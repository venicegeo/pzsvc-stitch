package stitch


import grails.transaction.Transactional
import javax.imageio.ImageIO


@Transactional
class StitchService {

	def grailsApplication
	def httpDownloadService

	
	def convertImageDataToFile(imageData) {
		def decodedBytes = imageData.bytes.decodeBase64()
		def file = writeStreamToFile(decodedBytes)

		
		return file
	}

	def downloadImage(url) {
		def inputStream = httpDownloadService.serviceMethod([url: url])
		def file = writeStreamToFile(inputStream)


		return file
	}

	def generateFilename() { return "${grailsApplication.config.tempDirectory}/${new Date().format("yyyyMMddHHmmssSSS")}" }

	def serviceMethod(params) {
		def finishedProduct = generateFilename() as File

		def array = []
		if (params.imageUrls) { array = params.imageUrls.split(",") }
		else if (params.imageData) { array = params.imageData.split(",") }
		
		def files = []
		array.each() {
			def file = params.imageUrls ? downloadImage(it) : convertImageDataToFile(it)
			files.push(file)
		}	
		if (files.size() > 0) { finishedProduct = stitchFiles(files, params.outputFormat) }
		

		return finishedProduct
	}

	def stitchFiles(files, format) {
		def command
		def filename = generateFilename()
		if (format ==~ /pdf/) { 
			command = "convert ${files.collect({ it.absolutePath }).join(" ")} ${filename}.pdf" 
			def process = command.execute()
			process.waitFor()

			filename += ".pdf"
		}
		else if (format ==~ /gif/) { 
			command = "convert -delay 100 ${files.collect({ it.absolutePath }).join(" ")} ${filename}.gif" 
			def process = command.execute()
			process.waitFor()

			filename += ".gif"
		}
		else if (format ==~ /avi|mov|wmv/) {
			command = "convert -delay 100 ${files.collect({ it.absolutePath }).join(" ")} ${filename}.gif"
			def process = command.execute()
			process.waitFor()


			command = "ffmpeg -r 1 -i ${filename}.gif -q:v 1 -r 1 ${filename}.${format}"
			process = command.execute()
			process.waitFor()

			filename += ".${format}"
		}


		return filename as File
	}

	def writeStreamToFile(stream) {
		def byteArrayInputStream = new ByteArrayInputStream(stream)
		def bufferedImage = ImageIO.read(byteArrayInputStream)
		def file = "${generateFilename()}.png" as File
		ImageIO.write(bufferedImage, "png", file)

		
		return file
	}
}
