package com.sep29th.rest.webservices.restfultwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	private static List<User> users = new ArrayList<>();
	private static int countUser = 0;
	static {
		users.add(new User(countUser++, "Adam", LocalDate.now().minusYears(25)));
		users.add(new User(countUser++, "Yasuo", LocalDate.now().minusYears(20)));
		users.add(new User(countUser++, "Zed", LocalDate.now().minusYears(28)));
	}

	public List<User> findAll() {
		return users;
	}

	public User findOne(int id) {
		Predicate<? super User> predicate = user -> user.getId() == id;
//		return users.stream().filter(predicate).findFirst().get();
		return users.stream().filter(predicate).findFirst().orElse(null);
	}

	public void deleteUser(int id) {
		Predicate<? super User> predicate = user -> user.getId() == id;
		users.removeIf(predicate);
	}

	public List<User> getWithCondition(User sampleUser) {
		return users.stream().filter(user -> {
			if (sampleUser.getName() != null && !sampleUser.getName().equals(user.getName())) {
				return false;
			}
			if (sampleUser.getBirthDate() != null && !sampleUser.getBirthDate().equals(user.getBirthDate())) {
				return false;
			}
			return true;
		}).collect(Collectors.toList());
	}

	public User save(User user) {
		user.setId(countUser++);
		users.add(user);
		return user;
	}

	public User updatePut(int id, User userUpdate) {
		userUpdate.setId(id);
		boolean[] check = { false };
		users = users.stream().map(user -> {
			if (user.getId() == id) {
				check[0] = true;
				return userUpdate;
			} else {
				return user;
			}
		}).collect(Collectors.toList());
		return check[0] ? userUpdate : null;
	}

}
