#! /usr/bin/python3

import sys
import os
import subprocess

# Paths relative to this file (evaluate.py)
inputPath = "./input/"
refPath = "./"
srcPath = "../src/"
# Keep empty
classpath = ""
# Outputs files (don't change this, it's set in the main)
outErr = sys.stderr
outScore = sys.stdout
outVerbose = open(os.devnull,"w")

################################################################################
def compileCompiler() :
  print("Compiling Compiler.java...", end="", file=outVerbose)
  proc = subprocess.Popen("cd %s && javac Compiler.java"%srcPath, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
  errMess = proc.stderr.read().decode('utf8')
  returnCode = proc.wait()
  if returnCode == 0 :
    print("Done", file=outVerbose)
  else :
    print("", file=outVerbose)
    print("ERROR !", file=outErr)
    print(errMess, file=outErr)
    exit(1)
  print("", file=outVerbose)
################################################################################

################################################################################
def deleteClasses() :

  for root, subdirs, files in os.walk("%s.."%srcPath) :
    if ".git" in root :
      continue
    for filename in files :
      if os.path.splitext(filename)[1] == ".class" :
        os.remove(root+"/"+filename)
        
  return classpath
################################################################################

################################################################################
def findClasspath() :
  global classpath

  if len(classpath) > 0 :
    return classpath

  for root, subdirs, files in os.walk("%s.."%srcPath) :
    if ".git" in root :
      continue
    for filename in files :
      if os.path.splitext(filename)[1] == ".class" :
        classpath += ("" if len(classpath) == 0 else ":") + root
        break
        
  return classpath
################################################################################

################################################################################
def compiler() :
  return "java -classpath %s Compiler -v 2"%findClasspath()
################################################################################

################################################################################
def green(string) :
  return "\033[92m%s\033[0m"%string
################################################################################

################################################################################
def purple(string) :
  return "\033[95m%s\033[0m"%string
################################################################################

################################################################################
def red(string) :
  return "\033[91m%s\033[0m"%string
################################################################################

################################################################################
def changeExtension(filename, newExtension) :
  return os.path.splitext(filename)[0] + newExtension
################################################################################

################################################################################
def findInputFiles() :
# Ignore programs err*.l
  inputFiles = []
  for filename in os.listdir(inputPath) :
    if os.path.splitext(filename)[1] == ".l" :
      if len(filename) < 3 or filename[0:3] != "err" :
        inputFiles.append(filename)
  return inputFiles
################################################################################

################################################################################
def deleteCompilationOutputs() :
  outputExtensions = [".exe", ".o", ".out", ".sa", ".saout", ".sc", ".ts", ".nasm", ".pre-nasm", ".c3a", ".c3aout", ".fg", ".fgs", ".ig"]
  for filename in os.listdir(inputPath) :
    if os.path.splitext(filename)[1] in outputExtensions :
      os.remove(inputPath+filename)
################################################################################

################################################################################
def compileInputFiles(inputFiles) :
  for inputFile in inputFiles :
    print("Compiling %s..."%inputFile, end="", file=outVerbose)
    returnCode = subprocess.Popen("{} {}{}".format(compiler(), inputPath, inputFile), shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE).wait()
    if returnCode == 0 :
      print("Done", file=outVerbose)
    else :
      print("ERROR !", file=outErr)
  print("", file=outVerbose)
################################################################################

################################################################################
def getNewEvaluationResult(name) :
  return [name, {"correct" : [], "incorrect" : [], "notfound" : []}]
################################################################################

################################################################################
def evaluateSa(inputFiles) :
  # Using compare_arbres
  evaluation = getNewEvaluationResult("Syntaxe Abstraite")

  for filename in inputFiles :
    saFilename = changeExtension(filename, ".sa")
    if not os.path.isfile(inputPath+saFilename) :
      evaluation[1]["notfound"].append(saFilename)
      continue
    
    saRef = refPath+"sa-ref/"+saFilename
    if not os.path.isfile(saRef) :
      print("ATTENTION : Fichier non trouvé : %s"%saRef, file=sys.stderr)
      continue

    # Here call compare_arbres
    res = subprocess.Popen("", shell=True, stdout=open(os.devnull, "w"), stderr=subprocess.PIPE).stderr.read()
    print(res)
    if "egaux" in str(res) :
      evaluation[1]["correct"].append(saFilename)
    else :
      evaluation[1]["incorrect"].append(saFilename)

  return evaluation
################################################################################

################################################################################
def evaluateDiff(inputFiles, extension, path, name) :
  evaluation = getNewEvaluationResult(name)

  for filename in inputFiles :
    producedFile = changeExtension(filename, extension)
    if not os.path.isfile(inputPath+producedFile) :
      evaluation[1]["notfound"].append(producedFile)
      continue
    
    ref = refPath+path+producedFile
    if not os.path.isfile(ref) :
      print("ATTENTION : Fichier non trouvé : %s"%ref, file=sys.stderr)
      continue

    res = subprocess.Popen("diff {} {}{}".format(ref, inputPath, producedFile), shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE).stdout.read()
    if len(res.strip()) == 0 :
      evaluation[1]["correct"].append(producedFile)
    else :
      evaluation[1]["incorrect"].append(producedFile)

  return evaluation
################################################################################

################################################################################
def evaluateNasm(inputFiles) :
  for filename in inputFiles :
    nasmFilename = changeExtension(filename, ".nasm")
    objectFilename = changeExtension(filename, ".o")
    execFilename = changeExtension(filename, ".exe")
    outFilename = changeExtension(filename, ".out")
    if not os.path.isfile(inputPath+nasmFilename) :
      continue

    out = subprocess.Popen("cd {} && nasm -f elf -dwarf -g {}".format(inputPath+"..","input/"+nasmFilename), shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE).stderr.read()
    if not os.path.isfile(inputPath+objectFilename) :
      print(out, file=sys.stderr)
      continue
    out = subprocess.Popen("ld -m elf_i386 -o {}{} {}{}".format(inputPath,execFilename,inputPath,objectFilename), shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE).stderr.read()
    if not os.path.isfile(inputPath+execFilename) :
      print(out, file=sys.stderr)
      continue
    out = subprocess.Popen("{}{} > {}{}".format(inputPath,execFilename,inputPath,outFilename), shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE).stderr.read()
    if not os.path.isfile(inputPath+outFilename) :
      print(out, file=sys.stderr)
      continue

  return evaluateDiff(inputFiles, ".out", "out-ref/", "Execution du nasm")
################################################################################

################################################################################
def printListElements(destination, elements, colorFunction, useColor, resultStr) :
  if len(elements) == 0 :
    return
  maxColumnSize = len(max(elements, key=len))
  for filename in elements :
    if useColor :
      print("\t{}".format(colorFunction(filename)), file=destination)
    else :
      print("\t{:{}} {}".format(filename, maxColumnSize+2, resultStr), file=destination)
################################################################################

################################################################################
def printEvaluationResult(destination, evaluationResult, useColor) :
  name = evaluationResult[0]
  correct = evaluationResult[1]["correct"]
  incorrect = evaluationResult[1]["incorrect"]
  notfound = evaluationResult[1]["notfound"]

  nbCorrect = len(correct)
  nbTotal = len(correct) + len(incorrect) + len(notfound)

  score = 100.0*nbCorrect/nbTotal
  print("Évaluation de %s :"%name, file=destination)
  print("{}/{} correct ({:6.2f}%)".format(nbCorrect, nbTotal, score), file=destination)
  printListElements(destination, correct, green, useColor, "CORRECT")
  printListElements(destination, incorrect, purple, useColor, "INCORRECT")
  printListElements(destination, notfound, red, useColor, "NON-EXISTANT")
  return score
################################################################################

################################################################################
def printUsageAndExit() :
  print("USAGE : %s (-v for verbose output)"%sys.argv[0], file=outErr)
  exit(1)
################################################################################

################################################################################
if __name__ == "__main__" :

  verbose = False
  if len(sys.argv) > 1 :
    if sys.argv[1] == "-v" :
      verbose = True
      outVerbose = outScore
    else :
      printUsageAndExit()

  inputFiles = findInputFiles()
  deleteCompilationOutputs()

  compileCompiler()
  compileInputFiles(inputFiles)
  deleteClasses()

  saEvaluation = evaluateDiff(inputFiles, ".sa", "sa-ref/", "Arbre abstrait")
  saOutEvaluation = evaluateDiff(inputFiles, ".saout", "saout-ref/", "Execution de l'arbre abstrait")
  tsEvaluation = evaluateDiff(inputFiles, ".ts", "ts-ref/", "Table des Symboles")
  c3aEvaluation = evaluateDiff(inputFiles, ".c3aout", "c3aout-ref/", "Code 3 Adresses")
  nasmEvaluation = evaluateNasm(inputFiles)

  useColor = True

  if useColor :
    print("Légende : {}  {}  {}".format(green("CORRECT"), purple("INCORRECT"), red("NON-EXISTANT")), file=outVerbose)

  scores = [
    printEvaluationResult(outVerbose, saEvaluation, useColor),
    printEvaluationResult(outVerbose, saOutEvaluation, useColor),
    printEvaluationResult(outVerbose, tsEvaluation, useColor),
    printEvaluationResult(outVerbose, c3aEvaluation, useColor),
    printEvaluationResult(outVerbose, nasmEvaluation, useColor)
  ]

  if not verbose :
    print("\t".join(["SA","SAOUT","TS","C3A","NASM"]), file=outScore)
    print("\t".join(map(str,scores)), file=outScore)
################################################################################

