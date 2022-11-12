from tkinter import *
from tkinter import ttk
import os
# Set environment variable
os.environ['TK_SILENCE_DEPRECATION'] = "1"
root = Tk()
frm = ttk.Frame(root,padding = 10)
frm.grid()
root.mainloop()