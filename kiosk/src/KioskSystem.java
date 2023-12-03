import java.awt.Image;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class KioskSystem {

    static Vector<Menu> porkMenus = new Vector<Menu>();
    static Vector<Menu> mealMenus = new Vector<Menu>();
    static Vector<Menu> noodleMenus = new Vector<Menu>();
    static Vector<Menu> sideMenus = new Vector<Menu>();
    static Vector<Menu> drinkMenus = new Vector<Menu>();
    static Vector<Menu> order = new Vector<Menu>();
    static Vector<Integer> price = new Vector<Integer>();

    static Menu new_porkMenus(String product_name, int price) {

        Menu new_porkMenus = new Menu(product_name, price);
        KioskSystem.addToPork(new_porkMenus);
        return new_porkMenus;
    }

    static Menu new_mealMenus(String product_name, int price) {

        Menu new_mealMenus = new Menu(product_name, price);
        KioskSystem.addToMeal(new_mealMenus);
        return new_mealMenus;

    }

    static Menu new_noodleMenus(String product_name, int price) {

        Menu new_noodleMenus = new Menu(product_name, price);
        KioskSystem.addToNoodle(new_noodleMenus);
        return new_noodleMenus;

    }

    static Menu new_sideMenus(String product_name, int price) {

        Menu new_sideMenus = new Menu(product_name, price);
        KioskSystem.addToSide(new_sideMenus);
        return new_sideMenus;

    }

    static Menu new_drinkMenus(String product_name, int price) {

        Menu new_drinkMenus = new Menu(product_name, price);
        KioskSystem.addToDrink(new_drinkMenus);
        return new_drinkMenus;

    }

    static Integer new_Price(int price) {

        Integer new_Price = Integer.valueOf(price);
        KioskSystem.addToPrice(new_Price);
        return new_Price;
    }

    static String total_Price() {

        int sum = 0;
        Menu m;

        for (int i = 0; i < KioskSystem.getNumOrders(); i++) {
            m = KioskSystem.getOrder(i);
            sum = sum + m.val();
        }
        return "총 가격 :" + sum + "원";
    }

    // 이미지 크기조절하기!
    static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    private static void addToPrice(Integer new_Price) {
        price.add(new_Price);

    }

    private static void addToSide(Menu new_sideMenus) {

        sideMenus.add(new_sideMenus);
    }

    private static void addToNoodle(Menu new_noodleMenus) {
        noodleMenus.add(new_noodleMenus);

    }

    private static void addToMeal(Menu new_mealMenus) {
        mealMenus.add(new_mealMenus);
    }

    private static void addToPork(Menu new_porkMenus) {
        porkMenus.add(new_porkMenus);

    }

    private static void addToDrink(Menu new_drinkMenus) {
        porkMenus.add(new_drinkMenus);

    }

    static void setupMenu() {
        KioskSystem.new_porkMenus("등심돈까스", 8000);
        KioskSystem.new_porkMenus("안심돈까스", 8000);
        KioskSystem.new_porkMenus("치즈돈까스", 9000);
        KioskSystem.new_porkMenus("왕돈까스", 9000);
        KioskSystem.new_porkMenus("치킨까스", 8000);
        KioskSystem.new_mealMenus("알밥", 7000);
        KioskSystem.new_mealMenus("돈까스덮밥", 8000);
        KioskSystem.new_mealMenus("김치볶음밥", 7000);
        KioskSystem.new_noodleMenus("냉모밀", 7000);
        KioskSystem.new_noodleMenus("판모밀", 7000);
        KioskSystem.new_noodleMenus("우동", 7000);
        KioskSystem.new_noodleMenus("쫄면", 7000);
        KioskSystem.new_sideMenus("새우튀김(2ps)", 3000);
        KioskSystem.new_sideMenus("치킨 가라아게", 4000);
        KioskSystem.new_sideMenus("감자 고로케", 4000);
        KioskSystem.new_sideMenus("공기밥", 1000);
        KioskSystem.new_drinkMenus("콜라", 2000);
        KioskSystem.new_drinkMenus("제로콜라", 2000);
        KioskSystem.new_drinkMenus("사이다", 2000);
        KioskSystem.new_drinkMenus("환타", 2000);
    }

    @SuppressWarnings("unused")
    private static void new_Order_HotDrink(Menu new_porkMenus) {
        order.add(new_porkMenus);
    }

    static String Customer_order() {
        Menu m;
        System.out.println("\n--모든 주문 리스트 --");

        for (int i = 0; i < KioskSystem.getNumOrders(); i++) {
            m = KioskSystem.getOrder(i);
            System.out.print(i + 1 + ". ");
            m.output();
        }

        return null;

    }

    static Menu getOrder(int witch) {

        return order.elementAt(witch);
    }

    static int getNumOrders() {

        return order.size();
    }

    static int getNumPrices() {
        return price.size();
    }

    static Integer getPrice(int witch) {
        return price.elementAt(witch);
    }

    static Integer get_Price(int witch) {
        return price.get(witch);
    }

    public static void main(String[] args) {// Test

        Customer_order();
        price.get(1);
    }

}
