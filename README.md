# stitch
Create various "moving picture" files from single frame image data sets.


## Requirements
* Vagrant 1.8.1

(others automatically installed when using Vagrant)
* FFMPEG 
* Groovy 
* Grails 
* ImageMagick


## Example Usage
The general usage is to use GET or POST with the following URL: `http://localhost:8080/stitch/home/stitch` <br>
The following params may be supplied: <br>
`outputFormat=<avi,gif,mov,pdf,wmv>` <br>
`imageUrls=<url1>,<url2>,<url3>...<url_n>` OR `imageData=<base64_1>,<base64_2>,<base64_3>...<base64_n>` <br>

Examples: <br>
`outputFormat=pdf` <br>
`imageUrls=https://googledrive.com/host/0Bz9BVmvDh0packRZcm4tSndGSU0/01.jpg, https://googledrive.com/host/0Bz9B... 0Bz9BVmvDh0packRZcm4tSndGSU0/08.jpg` <br>
`imageData=iVBORw0KGgoAAAANSUhEUgAAAR8AAAEECAYAAAARRjAV...,iVBORw0KGgoAAAANSUhEUgAAAR4AAAEECAYAAAD+hFsr...,...,iVBORw0KGgoAAAANSUhEUgAAAR4AAAEDCAYAAADjgWuT...` <br>


## Live Demo
1. `vagrant up`.
2. `vagrant ssh`.
3. `cd sync/stitch`.
4. `grails run-app`.
5. Go to `http://localhost:8080/stitch/home`.
6. Fill out the form and hit the "Create" button.

