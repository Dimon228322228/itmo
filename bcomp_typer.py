#!/usr/bin/python


import time
import sys
from typing import final

from pynput.keyboard import Controller, Key

"""
Вводит комманды из файла за вас с клавиатуры, переводя их в бинарный формат.
В конце устанавливает IP на начало исполнения программы.  

Для установки библиотеки ввода:
pip install pynput

Можно передать названия файлов при вызове скрипта.
Чтобы адрес начала исполнения установился корректно, 
название файла с основной программой нужно передать последним аргументом. 
python bcomp_typer.py subProg.txt mainProg.txt

По-умолчанию читается файл из переменной FILE_PATH

Структура файла c командами:
2 числа в первой строке файла - адрес начала программы, адрес начала исполнения. 
каждая комманда на новой строке 

03D5 03FF
0200
4000
0100
"""
FILE_PATH = "commands_my.txt"

SLEEP_DELAY = 0.02

keyboard = Controller()
KEYS = {"enter_addr": Key.f4, "write": Key.f5}

def read_file(filename) -> list[str]:
    with open(filename, "r") as f:
        return f.readlines()

def type_command(string):
    for character in string:
        keyboard.type(character)
        time.sleep(SLEEP_DELAY)
    keyboard.tap(KEYS["write"])


def enter_addr(start_addr):
    keyboard.type(start_addr)
    time.sleep(SLEEP_DELAY)
    keyboard.tap(KEYS["enter_addr"])


def hex_to_bits(hex_num):
    return bin(int(hex_num, 16))[2:].zfill(16)


def process_file(filename):
    lines = read_file(filename=filename)
    prog_start_addr, exec_start_addr = map(hex_to_bits, lines.pop(0).split())
    print(f"Start addr: {prog_start_addr}, Exec addr: {exec_start_addr}")
    enter_addr(prog_start_addr)

    for command in lines:
        bits_command = hex_to_bits(command)
        type_command(bits_command)
        time.sleep(SLEEP_DELAY)
    if exec_start_addr:
        enter_addr(exec_start_addr)


def main():
    print("Program will start in 4 seconds! Move your cursor on a bcomp app window.")
    time.sleep(4)
    args = sys.argv
    if len(args) == 1:
        print(f"No console args, reading from {FILE_PATH=}")
        process_file(FILE_PATH)
        return
    for file in args[1:]:
        print(f"Reading from {file}")
        process_file(file)

if __name__ == "__main__":
    main()
