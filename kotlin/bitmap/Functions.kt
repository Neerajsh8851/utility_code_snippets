    

/**
 * Loads scaled down bitmaps
 * @param filepath: path to image file
 * @param reqWidth: requested width 
 * @param reqHeight: requested height 
 */
suspend fun loadImage(filepath: String, reqWidth: Int, reqHeight: Int): Bitmap? = coroutineScope {
  withContext(Dispatchers.IO) {
      try {
          BitmapFactory.Options().run {
              inJustDecodeBounds = true
              BitmapFactory.decodeFile(filepath, this)
              inJustDecodeBounds = false
              inSampleSize = calculateInSampleSize(this, reqWidth, reqHeight)
              BitmapFactory.decodeFile(filepath, this).also {
                  console("loaded image from = $filepath, scaled size = ${it.width} x ${it.height}")
              }
          }
      } catch (ex: Exception) {
          ex.printStackTrace()
          null
      }
  }
}


fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
  val (height: Int, width: Int) = options.run { outHeight to outWidth }
  var returnedSample = 1
  var inSample = 2
  while (height / inSample >= reqHeight || width / inSample >= reqWidth) {
      returnedSample = inSample
      inSample *= 2
  }

  //console("returned inSampleSize = $returnedSample for actual size = $width x $height and requested size = $reqWidth x $reqHeight")
  return returnedSample
}
