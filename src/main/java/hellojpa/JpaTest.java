package hellojpa;

import static java.lang.System.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.*;

import hellojpa.domain.Address;
import hellojpa.domain.Album;
import hellojpa.domain.BaseEntity;
import hellojpa.domain.Book;
import hellojpa.domain.Child;
import hellojpa.domain.GrandParent;
import hellojpa.domain.Member;
import hellojpa.domain.Movie;
import hellojpa.domain.Parent;
import hellojpa.domain.Period;
import hellojpa.domain.Team;
import hellojpa.test.BookMark;
import hellojpa.test.File;
import hellojpa.test.Folder;

public class JpaTest {

	private static EntityManagerFactory emf;
	private static EntityManager em;

	static {
		emf = Persistence.createEntityManagerFactory("hello");
		em = emf.createEntityManager();
	}

	public static void main(String[] args) {

		testWithCommitTemplate();

		close();
	}

	private static void testWithCommitTemplate() {
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			// test101(); // mapped class 상속관계

			// folderTest();  // 핑구님의 폴더 전설의 시작 // dfs 저장

			folderTest2(); // 추상화

			// lazyAndEagerLoadingTest(); // 즉시, 지연 로딩 테스트

			// grandParentsCascadeTest(); // cascade 테스트

			// valueTypeTest(); // 값 타입 테스트트

			// valueTypeCollectionTest(); // 값 타입 컬렉션 테스트

			out.println("----------------------- commit start -----------------------");
			tx.commit();
			out.println("----------------------- commit end -----------------------");
		} catch (Exception e) {
			out.println("rollback !");
			tx.rollback();
			e.printStackTrace();
		}
	}

	private static void folderTest2() {

		Folder aFolder = new Folder();
		aFolder.setName("folder a").setParent(null);

		Folder bFolder = new Folder();
		bFolder.setName("folder b");

		BookMark aBookMark = new BookMark();
		aBookMark.setUrl("www.naver.com").setName("bookmark a");


		BookMark bBookMark = new BookMark();
		bBookMark.setUrl("www.daum.net").setName("bookmark b");

		BookMark cBookMark = new BookMark();
		cBookMark.setUrl("www.google.com").setName("bookmark c");

		aFolder.addChildren(bFolder, aBookMark);
		bFolder.addChildren(bBookMark, cBookMark);

		em.persist(aFolder);
		em.persist(bFolder);

		em.persist(aBookMark);
		em.persist(bBookMark);
		em.persist(cBookMark);

		long rootFileId = aFolder.getId();
		em.flush();
		em.clear();
		
		// 모든 폴더 찾아내기
		// File findRootFile = em.find(File.class, rootFileId);
		// out.println("findFolder = " + findRootFile);

		List<File> findRootFiles = em.createQuery("select f from File f", File.class).getResultList();
		out.println("findRootFiles = " + findRootFiles);


		// Queue<File> queue = new LinkedList<>();
		// queue.offer(findRootFile);
		//
		// while (!queue.isEmpty()) {
		// 	File pollFile = queue.poll();
		// 	out.println("pollFile.getName() = " + pollFile.getName());
		// 	if (pollFile instanceof Folder) {
		// 		Folder pollFolder = (Folder)pollFile;
		// 		List<File> pollChildren = pollFolder.getChildren();
		// 		pollChildren.forEach(queue::offer);
		// 	}
		// }

		// em.remove(findRootFile);
	}

	private static void valueTypeCollectionTest() {

		Set<String> favoriteFoods = new HashSet<>();
		favoriteFoods.add("간장 치킨");
		favoriteFoods.add("불닭 볶음면");
		favoriteFoods.add("돈까스");

		List<Address> addressHistory = new ArrayList<>();
		addressHistory.add(new Address("old city", "street", "zipcode"));
		addressHistory.add(new Address("new city", "street", "zipcode"));

		Member memberA = Member
			.builder()
			.name("user A")
			.favoriteFoods(favoriteFoods)
			.addressHistory(addressHistory)
			.build();

		em.persist(memberA);
	}

	private static void valueTypeTest() {

		LocalDateTime startDateTime = LocalDateTime.of(2020, 4, 1, 9, 0); // 내 입사일
		LocalDateTime endDateTime = LocalDateTime.of(2022, 2, 7, 18, 0); // 내 퇴사일 by 우테코 입교

		Address address = new Address("city A", "street", "zipcode");

		Member memberA = Member
			.builder()
			.name("user A")
			.homeAddress(address)
			.period(new Period(startDateTime, endDateTime))
			.build();

		Member memberB = Member
			.builder()
			.name("user B")
			.homeAddress(address)
			.period(new Period(startDateTime, endDateTime))
			.build();

		// boolean isWorkNow = memberA.getPeriod().isWork();

		Address newCity = new Address("new city", address.getStreet(), address.getZipcode());
		memberA.setHomeAddress(newCity);

		em.persist(memberA);
		em.persist(memberB);
	}

	private static void grandParentsCascadeTest() {
		GrandParent grandParentA = GrandParent.builder().name("grand parent A").build();
		grandParentA.setCreatedAt(LocalDateTime.now());

		Parent parentA = Parent.builder().name("parent A").build();
		Parent parentB = Parent.builder().name("parent B").build();

		Child childA = Child.builder().name("child A").build();
		Child childB = Child.builder().name("child B").build();
		Child childC = Child.builder().name("child C").build();

		grandParentA.addParents(parentA, parentB);

		parentA.addChildren(childA, childB);
		parentB.addChildren(childC);

		em.persist(grandParentA);

		em.flush();
		// em.clear();

		// grandParentA.getParents().remove(0);

		// em.remove(grandParentA);
	}

	private static void lazyAndEagerLoadingTest() {

		Member memberA = Member.builder().name("user A").build();
		Member memberB = Member.builder().name("user B").build();

		Team teamA = Team.builder().name("team A").build();
		Team teamB = Team.builder().name("team B").build();

		// memberA.addTeam(teamA);
		// memberB.addTeam(teamB);

		em.persist(teamA);
		em.persist(teamB);

		// 위 순서랑 바뀔시 업데이트 쿼리가 나간다 !
		em.persist(memberA);
		em.persist(memberB);

		em.flush();
		em.clear();

		// Member findMemberA = em.find(Member.class, memberA.getId());
		// out.println("findMemberA = " + findMemberA);

		// List<Member> findMemberList = em.createQuery("select m from Member m", Member.class).getResultList();
		// out.println("findMemberList = " + findMemberList);
		// out.println("findMemberList.getClass() = " + findMemberList.getClass());

		// 일대다쪽에서 EAGER 를 걸게 된다면?

		// Team findTeamA = em.find(Team.class, teamA.getId());
		// out.println("findTeamA = " + findTeamA);

		List<Team> findTeamList = em.createQuery("select t from Team t", Team.class).getResultList();
		out.println("findTeamList = " + findTeamList);

	}

	/*
		private static void folderTest() {
			Folder aFolder
				= Folder.builder().name("A").parent(null).build();

			Folder bFolder
				= Folder.builder().name("B").parent(aFolder).build();

			Folder cFolder
				= Folder.builder().name("C").parent(aFolder).build();

			Folder dFolder
				= Folder.builder().name("D").parent(aFolder).build();

			Folder bChild1
				= Folder.builder().name("B child 1").parent(bFolder).build();

			Folder bChild2
				= Folder.builder().name("B child 2").parent(bFolder).build();

			Folder bChild3
				= Folder.builder().name("B child 3").parent(bFolder).build();

			Folder cChild1
				= Folder.builder().name("C child 1").parent(cFolder).build();

			Folder cChild2
				= Folder.builder().name("C child 2").parent(cFolder).build();

			Folder dChild1
				= Folder.builder().name("D child 1").parent(dFolder).build();

			em.persist(aFolder);
			em.persist(bFolder);
			em.persist(cFolder);
			em.persist(dFolder);
			em.persist(bChild1);
			em.persist(bChild2);
			em.persist(bChild3);
			em.persist(cChild1);
			em.persist(cChild2);
			em.persist(dChild1);

			out.println("----------- root id ------------");
			long rootId = aFolder.getId();
			out.println("rootId = " + rootId);
			out.println("----------- root id ------------");

			em.flush();
			em.clear();

			// List<Folder> children = aFolder.getChildren();
			// out.println("children = " + children);

			out.println("----------------------------------------");
			Folder findFolder = em.find(Folder.class, aFolder.getId());
			out.println("findFolder = " + findFolder);
			out.println("----------------------------------------");

			Queue<Folder> queue = new LinkedList<>();
			queue.offer(findFolder);

			Queue<Folder> deleteList = new LinkedList<>();

			int i = 1;
			while (!queue.isEmpty()) {

				Folder pollFolders = queue.poll();

				List<Folder> children = pollFolders.getChildren();

				out.printf("--------------------- child use start :: %d---------------------\n", i);
				children.forEach(child -> queue.offer(child));
				out.printf("--------------------- child use end :: %d ---------------------\n", i);
				i++;

				deleteList.add(pollFolders);
			}

			em.flush();
			em.clear();

			Folder findRootFolder = em.find(Folder.class, rootId);
			out.println("--------------- remove -------------");
			em.remove(findRootFolder);
			out.println("--------------- remove -------------");

		}

	*/
	private static void test101() {

		// Item item = new Item();
		// item.setName("just Item");
		// item.setPrice(9_000);
		// em.persist(item);

		Album album = new Album();
		album.setArtist("윤하");
		album.setName("혜성");
		album.setPrice(50_000);
		em.persist(album);

		Book book = new Book();
		book.setName("JPA");
		book.setAuthor("영한 킴");
		book.setIsbn("0101-222-333");
		book.setPrice(40_000);
		em.persist(book);

		Movie movie = new Movie();
		movie.setPrice(15_000);
		movie.setName("기생충");
		movie.setDirector("봉준호");
		movie.setActor("박소담");
		em.persist(movie);
	}

	private static void close() {
		em.close();
		emf.close();
	}
}
