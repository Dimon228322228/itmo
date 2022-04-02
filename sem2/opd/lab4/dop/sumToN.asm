ORG 0x0

BEGIN:
  LD #5
  PUSH 
  CALL SUM_TO_N
  POP
  HLT

ORG 0xA

; Subprogram calculates sum from 1 to N
SUM_TO_N: 
  CLA
SUM_LOOP:
  ADD &1
  LOOP &1
  JUMP SUM_LOOP
  BPL RETURN
  CLA
RETURN:
  ST &1
  RET
