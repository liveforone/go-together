package gotogether.gotogether.member.repository

import com.linecorp.kotlinjdsl.querydsl.expression.column
import com.linecorp.kotlinjdsl.spring.data.SpringDataQueryFactory
import com.linecorp.kotlinjdsl.spring.data.singleQuery
import gotogether.gotogether.exception.exception.MemberCustomException
import gotogether.gotogether.exception.message.MemberMessage
import gotogether.gotogether.member.domain.Member
import jakarta.persistence.NoResultException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class MemberRepositoryImpl @Autowired constructor(
    private val queryFactory: SpringDataQueryFactory
) : MemberCustomRepository {

    override fun findOneById(id: Long): Member {
        return try {
            queryFactory.singleQuery {
                select(entity(Member::class))
                from(entity(Member::class))
                where(column(Member::id).equal(id))
            }
        } catch (e: NoResultException) {
            throw MemberCustomException(MemberMessage.MEMBER_IS_NULL.message)
        }
    }

    override fun findOneByEmail(email: String): Member {
        TODO("Not yet implemented")
    }

    override fun findOneByIdentity(identity: String): Member {
        TODO("Not yet implemented")
    }
}