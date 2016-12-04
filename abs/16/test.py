import os
import fnmatch

resx = []
def getResxfile(dir, resx):
  for root, dirnames, filenames in os.walk(dir):
    for filename in fnmatch.filter(filenames, '*.sh'):
      resx.append(os.path.join(root, filename))
    for subdir in dirnames:
      getResxfile(os.path.join(root, subdir), resx)

getResxfile(".", resx)
print resx
