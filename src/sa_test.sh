#!/bin/bash

for file in ../test/input/*.l; do
  echo "Compile : $file"
  res=$(java Compiler $file -v 2)
  NOEXT=$(echo $file | cut -f 1,2,3 -d '.')
  REF=$(echo $NOEXT | cut -f 4 -d '/')
  echo "Compute differences : $NOEXT.sa ../test/sa-ref/$REF.sa"
  RES=`diff $NOEXT.sa ../test/sa-ref/$REF.sa`
  if [ $? = 0 ]; then
    echo "Test passed. Sa files are identical."
  else
  echo $RES
    echo $res
    exit 1
  fi
  echo
done

