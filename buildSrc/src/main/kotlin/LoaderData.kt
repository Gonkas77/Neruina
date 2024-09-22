import org.gradle.api.Project

class LoaderData(private val project: Project, private val name: String) {
    val isForge = name == "forge"
    val isFabric = name == "fabric"
    val isNeoForge = name == "neoforge"

    fun getVersion() : String = when {
        isNeoForge -> project.property("neoforge_loader").toString()
        isForge -> project.property("forge_loader").toString()
        else -> project.property("fabric_loader").toString()
    }

    override fun toString(): String {
        return name
    }
}