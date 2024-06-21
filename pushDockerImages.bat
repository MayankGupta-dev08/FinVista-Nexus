@echo off
setlocal enabledelayedexpansion

REM Set the Docker Hub username
set "USERNAME=devmayank8"

REM Set the project and version and repository names
set "PROJECT=finvistanexus"
set "VERSION=3.1.0-SNAPSHOT"
set "REPOSITORY1=accounts"
set "REPOSITORY2=loans"
set "REPOSITORY3=cards"
set "REPOSITORY4=configserver"
set "REPOSITORY5=eurekaserver"

REM Define an array of images
set images[1]=%USERNAME%/%PROJECT%-%REPOSITORY1%:%VERSION%
set images[2]=%USERNAME%/%PROJECT%-%REPOSITORY2%:%VERSION%
set images[3]=%USERNAME%/%PROJECT%-%REPOSITORY3%:%VERSION%
set images[4]=%USERNAME%/%PROJECT%-%REPOSITORY4%:%VERSION%
set images[5]=%USERNAME%/%PROJECT%-%REPOSITORY5%:%VERSION%

REM Loop through the images and push each one
for /L %%i in (1,1,5) do (
  echo Pushing !images[%%i]!
  docker push !images[%%i]!
)

echo All images have been pushed.
endlocal
