import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import validation.basic.ValidatorUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dragon
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Room3 {

    @NotNull
    public String name;

    @AssertTrue
    public boolean finished;

    //3、容器元素级别约束（Container Element）
    //其实它是把List当作一个Bean，去验证List里面的标注有约束注解的属性/方法。
    // 很显然，List里面不可能标注有约束注解嘛，所以什么都不输出喽
//	public static void main(String[] args) {
//		List<@NotNull Room3> rooms = new ArrayList<>();
//		rooms.add(null);
//		rooms.add(new Room3());
//
//		Room3 room = new Room3();
//		room.name = "YourBatman";
//		rooms.add(room);
//
//		ValidatorUtil.printViolations(ValidatorUtil.obtainValidator().validate(rooms));
//	}

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    static class RoomList {
        protected List<@Valid @NotNull Room3> rooms;
    }

    //3、容器元素级别约束（Container Element）
    public static void main(String[] args) {
        List<@NotNull Room3> rooms = new ArrayList<>();
        rooms.add(null);
        rooms.add(new Room3());

        Room3 room = new Room3();
        room.name = "YourBatman";
        rooms.add(room);

        RoomList roomList = new RoomList(rooms);
        ValidatorUtil.printViolations(ValidatorUtil.obtainValidator().validate(roomList));
    }

}
