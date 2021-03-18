#!/bin/bash

for file in ../test/input/*.l; do
  echo "Compile : $file"
  res=$(java Compiler $file -v 2)
  NOEXT=$(echo $file | cut -f 1,2,3 -d '.')
  REF=$(echo $NOEXT | cut -f 4 -d '/')
  echo "Compute differences : $NOEXT.ts ../test/ts-ref/$REF.ts"
  RES=`diff $NOEXT.ts ../test/ts-ref/$REF.ts`
  if [ $? = 0 ]; then
    echo "Test passed. Ts files are identical."
  else
    echo $RES
    echo $res
    exit 1
  fi
  echo
done

