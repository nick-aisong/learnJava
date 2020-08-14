package commands
import org.crsh.cli.Command
import org.crsh.cli.Usage
import org.crsh.command.InvocationContext

class hello {
	@Usage("Say Hello") // 1使用@Usage注解解释该命令的用途
	@Command //2 使用@Command注解当前是一个CRaSH命令
	def main(InvocationContext context) {

		def bootVersion = context.attributes['spring.boot.version']; // 3获得Spring Boot的版本，注意Groovy的方法和变量声明关键字为def
		def springVersion = context.attributes['spring.version'] // 4获得Spring框架的版本

		return "Hello,your Spring Boot version is "+bootVersion +",your Spring Framework version is "+springVersion // 5返回命令执行结果 
	}
}