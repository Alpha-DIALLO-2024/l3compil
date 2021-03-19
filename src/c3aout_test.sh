#!/bin/bash

for file in ../test/input/*.l; do
  if [[ $file != *"err"* ]] ; then
    echo "Compile : $file"
    res=$(java Compiler $file -v 2)
    NOEXT=$(echo $file | cut -f 1,2,3 -d '.')
    REF=$(echo $NOEXT | cut -f 4 -d '/')
    echo "Compute differences : $NOEXT.c3aout ../test/c3aout-ref/$REF.c3aout"
    RES=`diff --strip-trailing-cr $NOEXT.c3aout ../test/c3aout-ref/$REF.c3aout`
    if [ $? = 0 ]; then
      echo "Test passed. c3aout files are identical."
    else
      echo $RES
      echo $res
      exit 1
    fi
  fi
done

