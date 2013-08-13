import sys
import os
print sys.argv
print os.path.dirname(sys.argv[0])
print os.path.abspath(os.path.dirname(sys.argv[0]))
from algorithms.sandminer import main_code
print dir(main_code) 