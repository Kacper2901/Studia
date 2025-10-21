from turtle import *
import time
import random

tracer(0,0)
FRAME_RATE = 1/30

pensize(3)

def kwadrat(bok, k):
    prostokat(bok, bok, k)
    
def prostokat(bok1, bok2, k):
    fillcolor(k)
    begin_fill()
    for i in range(2):
        fd(bok1)
        rt(90)
        fd(bok2)
        rt(90)
        
    end_fill()   