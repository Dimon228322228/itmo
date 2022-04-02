import json
from Executor import Executor
import itertools

def load_table(filename):
  with open(filename) as f:
    return json.load(f)

def count_rows_for_comment(comments):
  rows_for_comment = []
  first_comment = next(c for c in comments if c)
  shrinked_comments = comments[comments.index(first_comment)+1:]
  rows = 1
  for comment in shrinked_comments:
    if not comment: 
      rows += 1
      continue
    rows_for_comment.append(rows)
    rows = 1
  rows_for_comment.append(rows)

  return rows_for_comment

def get_table(latex=False):
  first_row = ["№", "Адрес", "Команда", "Мнемоника", "Описание", "Комментарий"]
  # try:
  if latex:
    executor = Executor("../commands_my.txt", "instructions2mnem.json")
    executor.gen_execution_table()
    executor.save_result("result_table.json")
    table_obj = load_table("result_table.json")
  else:
    table_obj = load_table("utils/result_table.json")
    executor = Executor("commands_my.txt", "utils/instructions2mnem.json")
    executor.gen_execution_table()
    executor.save_result("utils/result_table.json")
    

  latex_rows = " & ".join(first_row)

  comments = [ c["comment"] for c in table_obj.values()]
  rows_for_comments = count_rows_for_comment(comments)
  # rows_for_comments = [ len(list(g)) for comment, g in itertools.groupby(comments) if comment == "" ]
  c_counter = 0 

  for i, row in enumerate(table_obj.items()):
    addr = row[0] 
    other = row[1].values()
    comment = row[1]["comment"]

    latex_row = r"\\ \hline" + f"\n{i+1} & {addr} &" + "& ".join(other)
      
    if comment: # if comment exists
      latex_row = latex_row.replace(comment, "\\multirow{%s}{*}{%s}" % (rows_for_comments[c_counter], comment))
      c_counter += 1
    elif c_counter:
      latex_row = r"\\ \cline{1-5}" + f"\n{i+1} & {addr} &" + "& ".join(other)

    latex_rows += latex_row
    
  latex_rows = latex_rows.replace("#", r"\#")
  latex_rows = latex_rows.replace("$", r"\$")
  return latex_rows

if __name__ == "__main__":
    print(get_table())