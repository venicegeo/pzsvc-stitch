package stitch


import grails.transaction.Transactional


@Transactional
class RestApiService {

	def normalizeRequestParams(params, request) {
		def requestMap
		if (params.size() == 3) { 
			requestMap = request.reader.text.split('&').inject([:]) {
				map, token ->
				token.split('=').with { map[it[0]] = java.net.URLDecoder.decode(it[1]) }
				map
			}
		}
		else { requestMap = params }


		return requestMap
	}
}
