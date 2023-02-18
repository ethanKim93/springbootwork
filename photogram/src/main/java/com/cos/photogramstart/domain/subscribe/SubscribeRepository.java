package com.cos.photogramstart.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer>{
	
	@Modifying // INSERT ,DELETE , UPDATE를 네이티브 쿼리로 작성하려면 해당 어노테이션 필요 !!
	@Query(value = "INSERT INTO subscribe(fromUserId,toUserId,createDate) VALUES(:fromUserId,:toUserId,now())",nativeQuery = true)
	int mSubscribe(int fromUserId,int toUserId); // 1(변경된 행의 개수가 리턴됨),-1(에러) , 0은 에러는 아니고 변경된게 없울때
	
	@Modifying // INSERT ,DELETE , UPDATE를 네이티브 쿼리로 작성하려면 해당 어노테이션 필요 !!
	@Query(value = "DELETE FROM subscribe WHERE fromUserId = :fromUserId AND toUserId = :toUserId",nativeQuery = true)
	int mUnSubscribe(int fromUserId,int toUserId); // 1 , -1 
	
	// select만 하기 때문에 @Modifying이 필요 없음
	@Query(value = "SELECT COUNT(*) FROM subscribe WHERE fromUserId = :principalId and toUserId = :pageUserId ",nativeQuery = true) 
	int mSubscribeState(int principalId,int pageUserId);
	
	@Query(value = "SELECT COUNT(*) FROM subscribe WHERE fromUserId = :pageUserId",nativeQuery = true) 
	int mSubscribeCount(int pageUserId);
}
