import org.gradle.api.Project

class LoaderData(private val project: Project, private val name: String) {
    val isFabric = name == "fabric"
    val isNeoForge = name == "neoforge"

    fun getVersion() : String = when {
        isNeoForge -> project.property("neoforge_loader").toString()
        isFabric -> project.property("fabric_loader").toString()
        else -> throw IllegalStateException("$name is not supported")
    }

    override fun toString(): String {
        return name
    }
}