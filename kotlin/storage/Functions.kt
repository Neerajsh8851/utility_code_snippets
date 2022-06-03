
    /**
     * traverse in file tree
     * @param parent: root to start from
     * @param visit: a callback, that is called on each file found. Return a true to stop the traversing
     */
    fun fileTreeVisitor(parent: File, visit: (File) -> Boolean) {
        val children = parent.listFiles()
        if (children != null) {
            for (child in children) {
                if (child.isFile) {
                    if (visit(child)) return
                } else {
                    fileTreeVisitor(child, visit)
                }
            }
        }
    }
    
  
