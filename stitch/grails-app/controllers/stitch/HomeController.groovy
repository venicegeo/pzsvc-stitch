package stitch


import javax.imageio.ImageIO


class HomeController {

	def httpDownloadService
	def restApiService
	def stitchService


	def index() { render(view: "demo.gsp") }

	def proxy() {
		def inputStream = httpDownloadService.serviceMethod(params)
		def byteArrayInputStream = new ByteArrayInputStream(inputStream)
 		def bufferedImage = ImageIO.read(byteArrayInputStream)


		response.contentType = "image/png"
		ImageIO.write(bufferedImage, "png", response.outputStream)
		response.outputStream.flush()
		response.outputStream.close()
	}

	def stitch() {
		def model = restApiService.normalizeRequestParams(params, request)
		def file = stitchService.serviceMethod(model)
		if (file.exists()) {
                        def bytes = file.getBytes()

			def contentType
			switch (params.outputFormat) {
				case "avi": contentType = "video/avi"; break
				case "gif": contentType = "image/gif"; break
				case "mov": contentType = "video/quicktime"; break
				case "pdf": contentType = "application/pdf"; break
				case "wmv": contentType = "video/x-ms-wmv"; break
			}
                        response.contentType = contentType
                        response.outputStream << bytes
                        response.outputStream.flush()
			response.outputStream.close()
                }
                else { render "There was a problem stitching your images." }
	}
}
