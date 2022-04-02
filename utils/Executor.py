import json
import logging
from collections import defaultdict
from itertools import islice

Hex_str = str

# create logger
log = logging.getLogger(__name__) 

def _load_all_instructions(instructions_filename) -> dict:
  with open(instructions_filename, "r") as f:
    return json.load(f)

def _hex2num(Hex_str) -> int:
  num = int(Hex_str, 16)
  if num > 127:
    return num - 256
  return num

def add_int_to_hex(hex_str: Hex_str, num) -> Hex_str:
  return hex(int(hex_str, 16) + num)[2:].upper()


class Executor:
  def __init__(self,filename: str, instructions_filname):
    self.filename = filename
    self.instructions_filename = instructions_filname
    self.all_instructions = _load_all_instructions(self.instructions_filename)
    self.execution_table = defaultdict(lambda: {None: {"instr": None, "mnem": None, "descr": "", "comment": ""}})
    self.current_program = dict[str: str]
    
  def _load_program(self) -> list[str]:
    with open(self.filename, "r") as f:
      return f.readlines()
    
  def save_result(self, filename):
    with open(filename, "w") as f:
      json.dump(self.execution_table, f, ensure_ascii=False, indent=2)

  def gen_execution_table(self) -> dict[str, str, str, str]:
    var_names = {"X1": "Array Address", "X2": "Array Counter", "M": "Loop Counter", "R": "Result"}
    array_name = "Y"

    program_lines = self._load_program()
    
    parsed_program = self._parse_program(program_lines)

    self._fill_table_with_variables(var_names, parsed_program["vars_dict"])
    self._fill_table_with_array_names(array_name, parsed_program["arrays_dict"])

    for addr, instr in parsed_program["commands_dict"].items():
      instr_info = self._parse_new_instruction(instr)
      details = self._get_instr_result_details(addr, instr, instr_info)
      self.execution_table[addr] = {"instr": instr} | details | { "comment": ""}
      
    flatten_table = sorted(list(self.execution_table.items()), key=lambda x: int(x[0], 16))
    self.execution_table = {}
    for (addr, other), comment in zip(flatten_table, parsed_program["comments"]):
      self.execution_table[addr] = other
      self.execution_table[addr]["comment"] = comment

    
  def _parse_program(self, program_lines) -> dict:
    splitted_lines = list(map(lambda x: x.strip().split(maxsplit=2), program_lines))

    start_addr = splitted_lines[0][1]
    splitted_lines.pop(0)  # remove start address marked with '+'

    comments = [ line[2] if len(line) > 2 else "" for line in splitted_lines]

    addressToCommand = {line[0]: line[1] for line in splitted_lines}
    self.current_program = addressToCommand
    start_idx = list(addressToCommand.keys()).index(start_addr)
    
    end_idx = list(addressToCommand.values())[start_idx:].index("0100") + start_idx  # get idx of HLT command
    end_addr = list(addressToCommand.keys())[end_idx]

    vars_dict = dict(islice(addressToCommand.items(), 0, start_idx))
    commands_dict = dict(islice(addressToCommand.items(), start_idx, end_idx+1))
    arrays_dict = dict(islice(addressToCommand.items(), end_idx+1, len(addressToCommand)))
    return {"start_addr": start_addr, "end_addr": end_addr, 
            "start_idx": start_idx, "end_idx": end_idx, 
            "vars_dict": vars_dict,
            "commands_dict": commands_dict,
            "arrays_dict": arrays_dict, 
            "comments": comments}
    
  def _fill_table_with_variables(self, var_names, vars_dict):
    for (var, descr), (addr, instr) in zip(var_names.items(), vars_dict.items()):
      self.execution_table[addr] = { "instr": instr, "mnem": var, "descr": descr, "comment" : ""}

  def _fill_table_with_array_names(self, array_name, arrays_dict):
    for i, (addr, instr) in enumerate(arrays_dict.items()):
      self.execution_table[addr] = { "instr": instr, "mnem": f"{array_name}{i+1}", "descr": "Array Number", "comment" : ""}
    

  # Получает информацию о команде из загруженного файла
  def _parse_new_instruction(self, instr: Hex_str) -> dict:
    instruction_info = None
    
    keys = [instr, instr[0], instr[:2]]  # ключи для поиска команд
    for key in keys:
      instruction_info = self.all_instructions.get(key, instruction_info)
    if instruction_info is None:
      log.ERROR(f"Instruction {instr} not found in list of instructions!")
    return instruction_info

  # Здесь нужно также создавать описание команды, заменив M на мнемонику, записанную в таблице
  def _get_instr_result_details(self, addr: Hex_str, instr: Hex_str, instr_info: dict) -> dict[str: str]:
    instr_name = instr_info["name"]

    result = {"mnem": instr_name, "description": instr_info["description"]}

    if instr_info["type"] == "io":
      log.error(f"Instruction {instr} not supported")
    if instr_info["type"] == "addressless":
      return result
    if instr_info["type"] == "branch":
      result["mnem"] += f" IP{_hex2num(instr[2:]):+}"
      return result

    # "address":
    addr_info = Executor._determine_addr_type(instr)
    addr_value = addr_info["value"]

    # абсолютный адрес. Получить по этому адресу мнемонику
    if addr_info["type"] == "absolute":  # 0MMM
      revealed_mnemonic = self.execution_table[addr_value]["mnem"]
      result["description"] = self._generate_description(revealed_mnemonic, instr_info["description"])
      result["mnem"] += f" ${addr_value}"
      return result

    # прямая загрузка числа. Записать это число в мнемонику
    if addr_info["type"] == "direct":  # F
      result["description"] = self._generate_description(addr_value, instr_info["description"])
      result["mnem"] += f" #{addr_value}"
      return result
    
    shift = int(addr_value)

    # Относительные адреса высчитываются через IP + shift
    ip_addr = add_int_to_hex(addr, 1)
    revealed_addr = add_int_to_hex(ip_addr, shift)
    if "mnem" not in self.execution_table[revealed_addr]:
      print("err")
    mnemonic_value = self.execution_table[revealed_addr]["mnem"]
    if instr_name == "JUMP":  # костыль 
      mnemonic_value = revealed_addr
    result["mnem"] += f" (IP{shift:+})"

    if addr_info["type"] == "relative":
      result["description"] = self._generate_description(mnemonic_value, instr_info["description"])
      return result

    result["description"] = self._generate_description(f"MEM({mnemonic_value})", instr_info["description"])
    if addr_info["type"] == "indirect+":
      result["mnem"] += "+"
      result["description"] += f";{mnemonic_value} + 1 → {mnemonic_value}"
    if addr_info["type"] == "indirect-":
      result["mnem"] += "-"
      result["description"] += f"; {mnemonic_value} - 1 → {mnemonic_value}"
    
    return result
  
  
  # Заменить название переменной в описании результата
  def _generate_description(self, mnemonic_value, description_template) -> str:
    new_description = description_template.replace("M", mnemonic_value)
    return new_description
  
  # Возвращает тип адресной команды вместе с записанным в ней значением 
  @classmethod
  def _determine_addr_type(cls, instr: Hex_str) -> dict[str: str]:
    third_byte_int = int(instr[1], 16)
    value = str(_hex2num(instr[2:]))
    return_dict = defaultdict(
      lambda: {"type": "absolute", "value": instr[1:]},  # third_byte_int < 8
      { 10: {"type": "indirect+", "value": value},
        11: {"type": "indirect-", "value": value},
        14: {"type": "relative", "value": value},
        15: {"type": "direct", "value": value}
      }
    )
    return return_dict[third_byte_int]


if __name__ == "__main__":
    # create and configure main logger
  log= logging.getLogger(__name__)
  log.setLevel(logging.DEBUG)
  # create console handler with a higher log level
  handler = logging.StreamHandler()
  handler.setLevel(logging.DEBUG)
  # create formatter and add it to the handler
  formatter = logging.Formatter(
      '%(asctime)s - %(name)s - %(levelname)s - %(message)s')
  handler.setFormatter(formatter)
  # add the handler to the logger
  log.addHandler(handler)

  executor = Executor("commands_my.txt", "utils/instructions2mnem.json")
  executor.gen_execution_table()
  executor.save_result("utils/result_table.json")