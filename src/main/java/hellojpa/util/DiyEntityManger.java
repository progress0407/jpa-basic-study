package hellojpa.util;

import java.util.HashMap;
import java.util.Map;

import hellojpa.domain.Member;

public class DiyEntityManger {
	public static final String NOT_FOUND_MEMBER_EXCEPTION = "[ERROR] 존재하지 않는 Member 입니다.";

	private static Map<Long, Member> members = new HashMap<>();

	private void persist(Member member) {
		members.put(member.getId(), member);
	}

	private Member find(Long id) {

		Member findMember = members.get(id);
		if (findMember != null) {
			return findMember;
		}
		throw new IllegalArgumentException(NOT_FOUND_MEMBER_EXCEPTION);
	}
}
