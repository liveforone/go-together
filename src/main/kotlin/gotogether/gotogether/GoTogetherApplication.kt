package gotogether.gotogether

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.web.filter.HiddenHttpMethodFilter

@SpringBootApplication
@EnableJpaAuditing
class GoTogetherApplication

fun main(args: Array<String>) {
	runApplication<GoTogetherApplication>(*args)

	@Bean
	fun hiddenMethodFilter(): HiddenHttpMethodFilter = HiddenHttpMethodFilter()
}
