import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class KioskSystemUsingGUI {

    // JPanel속성을 상속받은 이미지 패널 설정, 이미지를 그리는 도구
    // @SuppressWarnings("serial")
    static class ImagePanel extends JPanel {
        // 1번방식: img를 먼저 선언하고 아래에 경로 써주기
        private Image img;
        // 2번방식: img를 선언하고 경로도 한번에 같이 써주기
        private Image manual_img = new ImageIcon("C:\\Users\\admin\\OneDrive\\workspace\\kiosk\\images\\사용설명서.png")
                .getImage();

        public ImagePanel(Image img) {
            this.img = img;

        }

        // 그리기 도구 배경화면, 사용서 이미지 그리기
        public void paintComponent(Graphics g) {
            g.drawImage(img, 0, 0, 1900, 1200, null);
            g.drawImage(manual_img, 1050, 300, 600, 600, null);
        }
    }

    public static ImageIcon resizeImageIcon(String imagePath) {
        ImageIcon originalIcon = new ImageIcon(imagePath); // 이미지를 ImageIcon으로 로드
        Image originalImage = originalIcon.getImage(); // ImageIcon에서 Image 객체 추출
        Image scaledImage = originalImage.getScaledInstance(260, 195, Image.SCALE_SMOOTH); // 이미지 크기 조정
        return new ImageIcon(scaledImage); // 크기가 조정된 Image로부터 ImageIcon 생성하여 반환
    }

    public static void main(String[] args) {
        JFrame fr = new JFrame("식당 메뉴 키오스크");
        // 이미지 패널 그리기
        ImagePanel pn = new ImagePanel(
                new ImageIcon("C:\\Users\\admin\\OneDrive\\workspace\\kiosk\\images\\배경.jpg").getImage());
        fr.pack();
        fr.add(pn);
        JPanel poPanel = new JPanel(); // 돈까스 메뉴
        JPanel mePanel = new JPanel(); // 식사 메뉴
        JPanel noPanel = new JPanel(); // 면 메뉴
        JPanel siPanel = new JPanel(); // 디저트 메뉴를 띄우는 패널
        JPanel drPanel = new JPanel(); // 음료 메뉴
        JPanel orPanel = new JPanel(); // 주문 메뉴

        // 주문내역
        JTextArea ordertxt = new JTextArea();

        // 주문내역을 확인할수있는 텍스트 필드를 붙이고, 내용추가
        pn.add(ordertxt);
        ordertxt.append("<장바구니> \n\n");
        ordertxt.setBounds(1350, 300, 400, 600); // 주문한내역을 볼수있는 txtarea를 생성
        ordertxt.setEditable(false);
        ordertxt.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        ordertxt.setBackground(Color.orange);

        JTextArea listtxt = new JTextArea();
        listtxt.setBounds(0, 0, 1600, 600);
        listtxt.setFont(new Font("맑은 고딕", Font.BOLD, 12));

        JButton[] bt = new JButton[7];
        JButton order_bt = new JButton("주문하기");

        // 메뉴판 테이블 만들기
        String[] heading = new String[] { "상품명", "가격" };
        Object[][] data = new Object[][] {
                { "상품명", "가격" },
                { "등심 돈까스", "8000원" },
                { "안심 돈까스", "8000원" },
                { "치즈돈까스", "9000원" },
                { "왕돈까스", "9000원" },
                { "치킨까스", "8000원" },
                { "알밥", "7000원" },
                { "돈까스덮밥", "8000원" },
                { "김치볶음밥", "7000원" },
                { "냉모밀", "7000원" },
                { "판모밀", "7000원" },
                { "우동", "7000원" },
                { "쫄면", "7000원" },
                { "새우튀김(2ps)", "3000원" },
                { "치킨 가라아게", "4000원" },
                { "감자 고로케", "4000원" },
                { "공기밥", "1000원" },
                { "콜라", "2000원" },
                { "제로콜라", "2000원" },
                { "사이다", "2000원" },
                { "환타", "2000원" },

        };

        JTable table = new JTable(data, heading);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(250, 300, 800, 600);
        pn.add(scrollPane);
        table.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        table.setRowHeight(50);

        // 버튼들 위치와 크기 설정
        int width[] = { 300, 300, 300, 300, 300, 200, 200 };
        int height[] = { 150, 150, 150, 150, 150, 100, 100 };
        int x[] = { 80, 380, 680, 980, 1280, 30, 30 };
        int y[] = { 100, 100, 100, 100, 100, 300, 500 };

        // 이미지 패널 크기설정
        pn.setLayout(null);
        pn.setBounds(0, 0, 1900, 1200);
        // 팔 메뉴들 Vector 객체속에 미리 저장해놓기
        KioskSystem.setupMenu();

        // 메인 버튼 6개 설정
        bt[0] = new JButton("돈까스");
        bt[1] = new JButton("식사");
        bt[2] = new JButton("면");
        bt[3] = new JButton("사이드");
        bt[4] = new JButton("음료");
        bt[5] = new JButton("메인");
        bt[6] = new JButton("리스트");

        for (int i = 0; i < bt.length; i++) {
            pn.add(bt[i]);
            bt[i].setBounds(x[i], y[i], width[i], height[i]);
            // 폰트 설정
            bt[i].setFont(new Font("맑은 고딕", Font.BOLD, 50));
            // 버튼을 투명하게 만들고, 버튼글씨 색상 설정
            bt[i].setContentAreaFilled(false);
            bt[i].setBorderPainted(false);
            bt[i].setFocusPainted(false);
            bt[i].setForeground(Color.WHITE);
        }

        // 버튼 0번의 기능설정
        bt[0].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JButton srcBtn = (JButton) e.getSource();
                if (srcBtn == bt[0]) {
                    doPork(); // 돈까스 메뉴 표시

                    // poPanel 의 크기 및 색상
                    poPanel.setLayout(null);
                    poPanel.setBounds(250, 300, 1600, 600);
                    poPanel.setBackground(Color.white);

                    // poPanel 누를시, 나머지 패널을 보이지 않게하기
                    poPanel.setVisible(true);
                    mePanel.setVisible(false);
                    noPanel.setVisible(false);
                    siPanel.setVisible(false);
                    drPanel.setVisible(false);
                    orPanel.setVisible(false);
                    ordertxt.setVisible(true);

                    scrollPane.setVisible(false);
                    order_bt.setVisible(true);
                }
            }

            private void doPork() {
                // poPanel에 생성할 이지지 버튼들을 설정
                JButton poButton_등심돈까스 = new JButton("");
                fr.add(poPanel);
                poButton_등심돈까스
                        .setIcon(resizeImageIcon("C:\\Users\\admin\\OneDrive\\workspace\\kiosk\\images\\등심돈까스.png"));
                poPanel.add(poButton_등심돈까스);
                poButton_등심돈까스.setBounds(20, 20, 260, 195);
                // 메뉴이름 띄어줄 텍스트 필드 생성
                JTextField 등심돈까스 = new JTextField("등심돈까스 8000원");
                poPanel.add(등심돈까스);
                등심돈까스.setBounds(20, 215, 260, 30);
                등심돈까스.setEditable(false);

                // 아메리카노를 누르면 알림창이 뜨게끔 기능 설정
                poButton_등심돈까스.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton srcBtn = (JButton) e.getSource();
                        if (srcBtn == poButton_등심돈까스) {
                            int result = JOptionPane.showConfirmDialog(poButton_등심돈까스, "등심돈까스를 주문하시겟습니까?", "주문확인",
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if (result == JOptionPane.OK_OPTION) {
                                ordertxt.append((KioskSystem.porkMenus.get(0).toString()));
                                KioskSystem.order.add(KioskSystem.porkMenus.get(0));
                                KioskSystem.new_Price(8000);
                            }
                        }
                    }
                });

                JButton poButton_안심돈까스 = new JButton("");
                poButton_안심돈까스
                        .setIcon(resizeImageIcon("C:\\Users\\admin\\OneDrive\\workspace\\kiosk\\images\\안심돈까스.png"));
                poPanel.add(poButton_안심돈까스);
                poButton_안심돈까스.setBounds(280, 20, 260, 195);
                // 메뉴이름 띄어줄 텍스트 필드 생성
                JTextField 안심돈까스 = new JTextField("안심돈까스 8000원");
                poPanel.add(안심돈까스);
                안심돈까스.setBounds(280, 215, 260, 30);
                안심돈까스.setEditable(false);
                // 안심돈까스를 누르면 알림창이 뜨게끔 설정
                poButton_안심돈까스.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton srcBtn = (JButton) e.getSource();
                        if (srcBtn == poButton_안심돈까스) {
                            int result = JOptionPane.showConfirmDialog(poButton_안심돈까스, "안심돈까스를 주문하시겟습니까?", "주문확인",
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if (result == JOptionPane.OK_OPTION) {
                                ordertxt.append((KioskSystem.porkMenus.get(1).toString()));
                                KioskSystem.order.add(KioskSystem.porkMenus.get(1));
                                KioskSystem.new_Price(8000);

                            }

                        }
                    }
                });

                JButton poButton_치즈돈까스 = new JButton("");
                poButton_치즈돈까스
                        .setIcon(resizeImageIcon("C:\\Users\\admin\\OneDrive\\workspace\\kiosk\\images\\치즈돈까스.png"));
                poPanel.add(poButton_치즈돈까스);
                poButton_치즈돈까스.setBounds(540, 20, 260, 195);
                // 메뉴이름 띄어줄 텍스트 필드 생성
                JTextField 치즈돈까스 = new JTextField("치즈돈까스 9000원");
                poPanel.add(치즈돈까스);
                치즈돈까스.setBounds(540, 215, 260, 30);
                치즈돈까스.setEditable(false);
                // 치즈돈까스를 누르면 알림창이 뜨게끔 설정
                poButton_치즈돈까스.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton srcBtn = (JButton) e.getSource();
                        if (srcBtn == poButton_치즈돈까스) {
                            int result = JOptionPane.showConfirmDialog(poButton_치즈돈까스, "치즈돈까스를 주문하시겟습니까?", "주문확인",
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if (result == JOptionPane.OK_OPTION) {
                                ordertxt.append((KioskSystem.porkMenus.get(2).toString()));
                                KioskSystem.order.add(KioskSystem.porkMenus.get(2));
                                KioskSystem.new_Price(9000);

                            }

                        }
                    }
                });

                JButton poButton_왕돈까스 = new JButton("");
                poButton_왕돈까스
                        .setIcon(resizeImageIcon("C:\\Users\\admin\\OneDrive\\workspace\\kiosk\\images\\왕돈까스.png"));
                poPanel.add(poButton_왕돈까스);
                poButton_왕돈까스.setBounds(20, 245, 260, 195);
                // 메뉴이름 띄어줄 텍스트 필드 생성
                JTextField 왕돈까스 = new JTextField("왕돈까스 9000원");
                poPanel.add(왕돈까스);
                왕돈까스.setBounds(20, 440, 260, 30);
                왕돈까스.setEditable(false);
                // 왕돈까스를 누르면 알림창이 뜨게끔 설정
                poButton_왕돈까스.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton srcBtn = (JButton) e.getSource();
                        if (srcBtn == poButton_왕돈까스) {
                            int result = JOptionPane.showConfirmDialog(poButton_왕돈까스, "왕돈까스를 주문하시겟습니까?", "주문확인",
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if (result == JOptionPane.OK_OPTION) {
                                ordertxt.append((KioskSystem.porkMenus.get(3).toString()));
                                KioskSystem.order.add(KioskSystem.porkMenus.get(3));
                                KioskSystem.new_Price(9000);

                            }

                        }
                    }
                });

                JButton poButton_치킨까스 = new JButton("");
                poButton_치킨까스
                        .setIcon(resizeImageIcon("C:\\Users\\admin\\OneDrive\\workspace\\kiosk\\images\\치킨까스.png"));
                poPanel.add(poButton_치킨까스);
                poButton_치킨까스.setBounds(280, 245, 260, 195);
                // 메뉴이름 띄어줄 텍스트 필드 생성
                JTextField 치킨까스 = new JTextField("치킨까스 8000원");
                poPanel.add(치킨까스);
                치킨까스.setBounds(280, 440, 260, 30);
                치킨까스.setEditable(false);
                // 치킨까스를 누르면 알림창이 뜨게끔 설정
                poButton_치킨까스.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton srcBtn = (JButton) e.getSource();
                        if (srcBtn == poButton_치킨까스) {
                            int result = JOptionPane.showConfirmDialog(poButton_치킨까스, "치킨까스를 주문하시겟습니까?", "주문확인",
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if (result == JOptionPane.OK_OPTION) {
                                ordertxt.append((KioskSystem.porkMenus.get(4).toString()));
                                KioskSystem.order.add(KioskSystem.porkMenus.get(4));
                                KioskSystem.new_Price(8000);

                            }

                        }
                    }
                });

            }

        }

        );
        // bt[0] 과 같은 형식임.
        bt[1].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JButton srcBtn = (JButton) e.getSource();
                if (srcBtn == bt[1]) {
                    doMeal();

                    mePanel.setLayout(null);
                    mePanel.setBounds(250, 300, 1600, 600);
                    mePanel.setBackground(Color.white);

                    poPanel.setVisible(false);
                    mePanel.setVisible(true);
                    noPanel.setVisible(false);
                    siPanel.setVisible(false);
                    drPanel.setVisible(false);
                    orPanel.setVisible(false);
                    ordertxt.setVisible(true);

                    scrollPane.setVisible(false);
                    order_bt.setVisible(true);
                }
            }

            private void doMeal() {
                JButton meButton_알밥 = new JButton("");
                fr.add(mePanel);
                meButton_알밥.setIcon(resizeImageIcon("C:\\Users\\admin\\OneDrive\\workspace\\kiosk\\images\\알밥.png"));
                mePanel.add(meButton_알밥);
                meButton_알밥.setBounds(20, 20, 260, 195);
                // 메뉴이름 띄어줄 텍스트 필드 생성
                JTextField 알밥 = new JTextField("알밥 7000원");
                mePanel.add(알밥);
                알밥.setBounds(20, 215, 260, 30);
                알밥.setEditable(false);
                // 버튼 기능 추가
                meButton_알밥.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton srcBtn = (JButton) e.getSource();
                        if (srcBtn == meButton_알밥) {
                            int result = JOptionPane.showConfirmDialog(meButton_알밥, "알밥을 주문하시겟습니까?",
                                    "주문확인", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if (result == JOptionPane.OK_OPTION) {
                                ordertxt.append((KioskSystem.mealMenus.get(0).toString()));
                                KioskSystem.order.add(KioskSystem.mealMenus.get(0));
                                KioskSystem.new_Price(7000);
                            }

                        }
                    }
                });

                JButton meButton_돈까스덮밥 = new JButton("");
                meButton_돈까스덮밥
                        .setIcon(resizeImageIcon("C:\\Users\\admin\\OneDrive\\workspace\\kiosk\\images\\돈까스덮밥.png"));
                mePanel.add(meButton_돈까스덮밥);
                meButton_돈까스덮밥.setBounds(280, 20, 260, 195);
                // 메뉴이름 띄어줄 텍스트 필드 생성
                JTextField 돈까스덮밥 = new JTextField("돈까스덮밥 8000원");
                mePanel.add(돈까스덮밥);
                돈까스덮밥.setBounds(280, 215, 260, 30);
                돈까스덮밥.setEditable(false);
                // 돈까스덮밥를 누르면 알림창이 뜨게끔 설정
                meButton_돈까스덮밥.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton srcBtn = (JButton) e.getSource();
                        if (srcBtn == meButton_돈까스덮밥) {
                            int result = JOptionPane.showConfirmDialog(meButton_돈까스덮밥, "돈까스덮밥을 주문하시겟습니까?", "주문확인",
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if (result == JOptionPane.OK_OPTION) {
                                ordertxt.append((KioskSystem.mealMenus.get(1).toString()));
                                KioskSystem.order.add(KioskSystem.mealMenus.get(1));
                                KioskSystem.new_Price(8000);

                            }

                        }
                    }
                });

                JButton meButton_김치볶음밥 = new JButton("");
                meButton_김치볶음밥
                        .setIcon(resizeImageIcon("C:\\Users\\admin\\OneDrive\\workspace\\kiosk\\images\\김치볶음밥.png"));
                mePanel.add(meButton_김치볶음밥);
                meButton_김치볶음밥.setBounds(540, 20, 260, 195);
                // 메뉴이름 띄어줄 텍스트 필드 생성
                JTextField 김치볶음밥 = new JTextField("김치볶음밥 7000원");
                mePanel.add(김치볶음밥);
                김치볶음밥.setBounds(540, 215, 260, 30);
                김치볶음밥.setEditable(false);
                // 김치볶음밥를 누르면 알림창이 뜨게끔 설정
                meButton_김치볶음밥.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton srcBtn = (JButton) e.getSource();
                        if (srcBtn == meButton_김치볶음밥) {
                            int result = JOptionPane.showConfirmDialog(meButton_김치볶음밥, "김치볶음밥을 주문하시겟습니까?", "주문확인",
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if (result == JOptionPane.OK_OPTION) {
                                ordertxt.append((KioskSystem.mealMenus.get(2).toString()));
                                KioskSystem.order.add(KioskSystem.mealMenus.get(2));
                                KioskSystem.new_Price(7000);

                            }

                        }
                    }
                });

            }

        }

        );

        bt[2].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JButton srcBtn = (JButton) e.getSource();
                if (srcBtn == bt[2]) {
                    doNoodle();

                    noPanel.setLayout(null);
                    noPanel.setBounds(250, 300, 1600, 600);
                    noPanel.setBackground(Color.white);
                    poPanel.setVisible(false);
                    mePanel.setVisible(false);
                    noPanel.setVisible(true);
                    siPanel.setVisible(false);
                    drPanel.setVisible(false);
                    orPanel.setVisible(false);
                    ordertxt.setVisible(true);

                    scrollPane.setVisible(false);
                    order_bt.setVisible(true);
                }
            }

            private void doNoodle() {
                JButton noButton_냉모밀 = new JButton("");
                fr.add(noPanel);
                noButton_냉모밀.setIcon(resizeImageIcon("C:\\Users\\admin\\OneDrive\\workspace\\kiosk\\images\\냉모밀.png"));
                noPanel.add(noButton_냉모밀);
                noButton_냉모밀.setBounds(20, 20, 260, 195);
                // 메뉴이름 띄어줄 텍스트 필드 생성
                JTextField 냉모밀 = new JTextField("냉모밀  7000원");
                noPanel.add(냉모밀);
                냉모밀.setBounds(20, 215, 260, 30);
                냉모밀.setEditable(false);
                noButton_냉모밀.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton srcBtn = (JButton) e.getSource();
                        if (srcBtn == noButton_냉모밀) {
                            int result = JOptionPane.showConfirmDialog(noButton_냉모밀, "냉모밀을 주문하시겟습니까?", "주문확인",
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if (result == JOptionPane.OK_OPTION) {
                                ordertxt.append((KioskSystem.noodleMenus.get(0).toString()));
                                KioskSystem.order.add(KioskSystem.noodleMenus.get(0));
                                KioskSystem.new_Price(7000);
                            }
                        }
                    }
                });

                JButton noButton_판모밀 = new JButton("");
                noButton_판모밀.setIcon(resizeImageIcon("C:\\Users\\admin\\OneDrive\\workspace\\kiosk\\images\\판모밀.png"));
                noPanel.add(noButton_판모밀);
                noButton_판모밀.setBounds(280, 20, 260, 195);
                // 메뉴이름 띄어줄 텍스트 필드 생성
                JTextField 판모밀 = new JTextField("판모밀  7000원");
                noPanel.add(판모밀);
                판모밀.setBounds(280, 215, 260, 30);
                판모밀.setEditable(false);
                // 판모밀를 누르면 알림창이 뜨게끔 설정
                noButton_판모밀.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton srcBtn = (JButton) e.getSource();
                        if (srcBtn == noButton_판모밀) {
                            int result = JOptionPane.showConfirmDialog(noButton_판모밀, "판모밀을 주문하시겟습니까?", "주문확인",
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if (result == JOptionPane.OK_OPTION) {
                                ordertxt.append((KioskSystem.noodleMenus.get(1).toString()));
                                KioskSystem.order.add(KioskSystem.noodleMenus.get(1));
                                KioskSystem.new_Price(7000);

                            }

                        }
                    }
                });

                JButton noButton_우동 = new JButton("");
                noButton_우동.setIcon(resizeImageIcon("C:\\Users\\admin\\OneDrive\\workspace\\kiosk\\images\\우동.png"));
                noPanel.add(noButton_우동);
                noButton_우동.setBounds(540, 20, 260, 195);
                // 메뉴이름 띄어줄 텍스트 필드 생성
                JTextField 우동 = new JTextField("우동  7000원");
                noPanel.add(우동);
                우동.setBounds(540, 215, 260, 30);
                우동.setEditable(false);
                // 우동를 누르면 알림창이 뜨게끔 설정
                noButton_우동.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton srcBtn = (JButton) e.getSource();
                        if (srcBtn == noButton_우동) {
                            int result = JOptionPane.showConfirmDialog(noButton_우동, "우동을 주문하시겟습니까?", "주문확인",
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if (result == JOptionPane.OK_OPTION) {
                                ordertxt.append((KioskSystem.noodleMenus.get(2).toString()));
                                KioskSystem.order.add(KioskSystem.noodleMenus.get(2));
                                KioskSystem.new_Price(7000);

                            }

                        }
                    }
                });

                JButton noButton_쫄면 = new JButton("");
                noButton_쫄면.setIcon(resizeImageIcon("C:\\Users\\admin\\OneDrive\\workspace\\kiosk\\images\\쫄면.png"));
                noPanel.add(noButton_쫄면);
                noButton_쫄면.setBounds(20, 245, 260, 195);
                // 메뉴이름 띄어줄 텍스트 필드 생성
                JTextField 쫄면 = new JTextField("쫄면  7000원");
                noPanel.add(쫄면);
                쫄면.setBounds(20, 440, 260, 30);
                쫄면.setEditable(false);
                // 쫄면를 누르면 알림창이 뜨게끔 설정
                noButton_쫄면.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton srcBtn = (JButton) e.getSource();
                        if (srcBtn == noButton_쫄면) {
                            int result = JOptionPane.showConfirmDialog(noButton_쫄면, "쫄면을 주문하시겟습니까?", "주문확인",
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if (result == JOptionPane.OK_OPTION) {
                                ordertxt.append((KioskSystem.noodleMenus.get(3).toString()));
                                KioskSystem.order.add(KioskSystem.noodleMenus.get(3));
                                KioskSystem.new_Price(7000);

                            }

                        }
                    }
                });

            }
        });

        bt[3].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JButton srcBtn = (JButton) e.getSource();
                if (srcBtn == bt[3]) {
                    doSide();

                    siPanel.setLayout(null);
                    siPanel.setBounds(250, 300, 1600, 600);
                    siPanel.setBackground(Color.white);

                    poPanel.setVisible(false);
                    mePanel.setVisible(false);
                    noPanel.setVisible(false);
                    siPanel.setVisible(true);
                    drPanel.setVisible(false);
                    orPanel.setVisible(false);
                    ordertxt.setVisible(true);

                    scrollPane.setVisible(false);
                    order_bt.setVisible(true);
                }
            }

            private void doSide() {
                JButton siButton_새우튀김 = new JButton("");
                fr.add(siPanel);
                siButton_새우튀김
                        .setIcon(resizeImageIcon("C:\\Users\\admin\\OneDrive\\workspace\\kiosk\\images\\새우튀김.png"));
                siPanel.add(siButton_새우튀김);
                siButton_새우튀김.setBounds(20, 20, 260, 195);
                // 메뉴이름 띄어줄 텍스트 필드 생성
                JTextField 새우튀김 = new JTextField("새우튀김(2ps)  3000원");
                siPanel.add(새우튀김);
                새우튀김.setBounds(20, 215, 260, 30);
                새우튀김.setEditable(false);
                // 새우튀김를 누르면 알림창이 뜨게끔 설정
                siButton_새우튀김.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton srcBtn = (JButton) e.getSource();
                        if (srcBtn == siButton_새우튀김) {
                            int result = JOptionPane.showConfirmDialog(siButton_새우튀김, "새우튀김(2ps)을 주문하시겟습니까?", "주문확인",
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if (result == JOptionPane.OK_OPTION) {
                                ordertxt.append((KioskSystem.sideMenus.get(0).toString()));
                                KioskSystem.order.add(KioskSystem.sideMenus.get(0));
                                KioskSystem.new_Price(3000);

                            }

                        }
                    }
                });

                JButton siButton_가라아게 = new JButton("");
                siButton_가라아게
                        .setIcon(resizeImageIcon("C:\\Users\\admin\\OneDrive\\workspace\\kiosk\\images\\가라아게.png"));
                siPanel.add(siButton_가라아게);
                siButton_가라아게.setBounds(280, 20, 260, 195);
                // 메뉴이름 띄어줄 텍스트 필드 생성
                JTextField 가라아게 = new JTextField("치킨 가라아게  4000원");
                siPanel.add(가라아게);
                가라아게.setBounds(280, 215, 260, 30);
                가라아게.setEditable(false);
                // 가라아게를 누르면 알림창이 뜨게끔 설정
                siButton_가라아게.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton srcBtn = (JButton) e.getSource();
                        if (srcBtn == siButton_가라아게) {
                            int result = JOptionPane.showConfirmDialog(siButton_가라아게, "치킨 가라아게를 주문하시겟습니까?", "주문확인",
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if (result == JOptionPane.OK_OPTION) {
                                ordertxt.append((KioskSystem.sideMenus.get(1).toString()));
                                KioskSystem.order.add(KioskSystem.sideMenus.get(1));
                                KioskSystem.new_Price(4000);

                            }

                        }
                    }
                });

                JButton siButton_고로케 = new JButton("");
                siButton_고로케.setIcon(resizeImageIcon("C:\\Users\\admin\\OneDrive\\workspace\\kiosk\\images\\고로케.png"));
                siPanel.add(siButton_고로케);
                siButton_고로케.setBounds(540, 20, 260, 195);
                // 메뉴이름 띄어줄 텍스트 필드 생성
                JTextField 고로케 = new JTextField("감자 고로케  4000원");
                siPanel.add(고로케);
                고로케.setBounds(540, 215, 260, 30);
                고로케.setEditable(false);
                // 고로케를 누르면 알림창이 뜨게끔 설정
                siButton_고로케.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton srcBtn = (JButton) e.getSource();
                        if (srcBtn == siButton_고로케) {
                            int result = JOptionPane.showConfirmDialog(siButton_고로케, "감자 고로케를 주문하시겟습니까?", "주문확인",
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if (result == JOptionPane.OK_OPTION) {
                                ordertxt.append((KioskSystem.sideMenus.get(2).toString()));
                                KioskSystem.order.add(KioskSystem.sideMenus.get(2));
                                KioskSystem.new_Price(4000);

                            }

                        }
                    }
                });

                JButton siButton_공기밥 = new JButton("");
                siButton_공기밥.setIcon(resizeImageIcon("C:\\Users\\admin\\OneDrive\\workspace\\kiosk\\images\\공기밥.png"));
                siPanel.add(siButton_공기밥);
                siButton_공기밥.setBounds(20, 245, 260, 195);
                // 메뉴이름 띄어줄 텍스트 필드 생성
                JTextField 공기밥 = new JTextField("공기밥  1000원");
                siPanel.add(공기밥);
                공기밥.setBounds(20, 440, 260, 30);
                공기밥.setEditable(false);
                // 공기밥를 누르면 알림창이 뜨게끔 설정
                siButton_공기밥.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton srcBtn = (JButton) e.getSource();
                        if (srcBtn == siButton_공기밥) {
                            int result = JOptionPane.showConfirmDialog(siButton_공기밥, "공기밥을 주문하시겟습니까?", "주문확인",
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if (result == JOptionPane.OK_OPTION) {
                                ordertxt.append((KioskSystem.sideMenus.get(3).toString()));
                                KioskSystem.order.add(KioskSystem.sideMenus.get(3));
                                KioskSystem.new_Price(1000);

                            }

                        }
                    }
                });

            }
        });

        bt[4].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JButton srcBtn = (JButton) e.getSource();
                if (srcBtn == bt[4]) {
                    doDrink();

                    drPanel.setLayout(null);
                    drPanel.setBounds(250, 300, 1600, 600);
                    drPanel.setBackground(Color.white);

                    poPanel.setVisible(false);
                    mePanel.setVisible(false);
                    noPanel.setVisible(false);
                    siPanel.setVisible(false);
                    drPanel.setVisible(true);
                    orPanel.setVisible(false);
                    ordertxt.setVisible(true);

                    scrollPane.setVisible(false);
                    order_bt.setVisible(true);
                }
            }

            private void doDrink() {
                JButton drButton_콜라 = new JButton("");
                fr.add(drPanel);
                drButton_콜라.setIcon(resizeImageIcon("C:\\Users\\admin\\OneDrive\\workspace\\kiosk\\images\\콜라.png"));
                drPanel.add(drButton_콜라);
                drButton_콜라.setBounds(20, 20, 260, 195);
                // 메뉴이름 띄어줄 텍스트 필드 생성
                JTextField 콜라 = new JTextField("콜라  2000원");
                drPanel.add(콜라);
                콜라.setBounds(20, 215, 260, 30);
                콜라.setEditable(false);
                // 콜라를 누르면 알림창이 뜨게끔 설정
                drButton_콜라.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton srcBtn = (JButton) e.getSource();
                        if (srcBtn == drButton_콜라) {
                            int result = JOptionPane.showConfirmDialog(drButton_콜라, "콜라를 주문하시겟습니까?", "주문확인",
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if (result == JOptionPane.OK_OPTION) {
                                ordertxt.append((KioskSystem.drinkMenus.get(0).toString()));
                                KioskSystem.order.add(KioskSystem.drinkMenus.get(0));
                                KioskSystem.new_Price(2000);

                            }

                        }
                    }
                });

                JButton drButton_제로콜라 = new JButton("");
                drButton_제로콜라
                        .setIcon(resizeImageIcon("C:\\Users\\admin\\OneDrive\\workspace\\kiosk\\images\\제로콜라.png"));
                drPanel.add(drButton_제로콜라);
                drButton_제로콜라.setBounds(280, 20, 260, 195);
                // 메뉴이름 띄어줄 텍스트 필드 생성
                JTextField 제로콜라 = new JTextField("제로콜라  2000원");
                drPanel.add(제로콜라);
                제로콜라.setBounds(280, 215, 260, 30);
                제로콜라.setEditable(false);
                // 제로콜라를 누르면 알림창이 뜨게끔 설정
                drButton_제로콜라.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton srcBtn = (JButton) e.getSource();
                        if (srcBtn == drButton_제로콜라) {
                            int result = JOptionPane.showConfirmDialog(drButton_제로콜라, "제로콜라를 주문하시겟습니까?", "주문확인",
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if (result == JOptionPane.OK_OPTION) {
                                ordertxt.append((KioskSystem.drinkMenus.get(1).toString()));
                                KioskSystem.order.add(KioskSystem.drinkMenus.get(1));
                                KioskSystem.new_Price(2000);

                            }

                        }
                    }
                });

                JButton drButton_사이다 = new JButton("");
                drButton_사이다.setIcon(resizeImageIcon("C:\\Users\\admin\\OneDrive\\workspace\\kiosk\\images\\사이다.png"));
                drPanel.add(drButton_사이다);
                drButton_사이다.setBounds(540, 20, 260, 195);
                // 메뉴이름 띄어줄 텍스트 필드 생성
                JTextField 사이다 = new JTextField("사이다  2000원");
                drPanel.add(사이다);
                사이다.setBounds(540, 215, 260, 30);
                사이다.setEditable(false);
                // 사이다를 누르면 알림창이 뜨게끔 설정
                drButton_사이다.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton srcBtn = (JButton) e.getSource();
                        if (srcBtn == drButton_사이다) {
                            int result = JOptionPane.showConfirmDialog(drButton_사이다, "사이다를 주문하시겟습니까?", "주문확인",
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if (result == JOptionPane.OK_OPTION) {
                                ordertxt.append((KioskSystem.drinkMenus.get(2).toString()));
                                KioskSystem.order.add(KioskSystem.drinkMenus.get(2));
                                KioskSystem.new_Price(2000);

                            }

                        }
                    }
                });

                JButton drButton_환타 = new JButton("");
                drButton_환타.setIcon(resizeImageIcon("C:\\Users\\admin\\OneDrive\\workspace\\kiosk\\images\\환타.png"));
                drPanel.add(drButton_환타);
                drButton_환타.setBounds(20, 245, 260, 195);
                // 메뉴이름 띄어줄 텍스트 필드 생성
                JTextField 환타 = new JTextField("환타  2000원");
                drPanel.add(환타);
                환타.setBounds(20, 440, 260, 30);
                환타.setEditable(false);
                // 환타를 누르면 알림창이 뜨게끔 설정
                drButton_환타.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton srcBtn = (JButton) e.getSource();
                        if (srcBtn == drButton_환타) {
                            int result = JOptionPane.showConfirmDialog(drButton_환타, "환타를 주문하시겟습니까?", "주문확인",
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if (result == JOptionPane.OK_OPTION) {
                                ordertxt.append((KioskSystem.drinkMenus.get(3).toString()));
                                KioskSystem.order.add(KioskSystem.drinkMenus.get(3));
                                KioskSystem.new_Price(2000);

                            }

                        }
                    }
                });

            }
        });

        // 메인화면 버튼 기능
        bt[5].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JButton srcBtn = (JButton) e.getSource();
                if (srcBtn == bt[5]) {

                    poPanel.setVisible(false);
                    mePanel.setVisible(false);
                    noPanel.setVisible(false);
                    siPanel.setVisible(false);
                    drPanel.setVisible(false);
                    orPanel.setVisible(false);
                    ordertxt.setVisible(false);

                    scrollPane.setVisible(true);
                    order_bt.setVisible(false);
                }

            }

        });

        // 주문리스트 버튼 기능
        bt[6].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JButton srcBtn = (JButton) e.getSource();
                if (srcBtn == bt[6]) {
                    fr.add(orPanel);
                    poPanel.setVisible(false);
                    mePanel.setVisible(false);
                    noPanel.setVisible(false);
                    siPanel.setVisible(false);
                    drPanel.setVisible(false);
                    orPanel.setVisible(true);
                    ordertxt.setVisible(false);

                    scrollPane.setVisible(false);
                    order_bt.setVisible(false);

                    orPanel.add(listtxt);
                    orPanel.setBounds(250, 300, 1600, 600);
                    orPanel.setBackground(Color.white);

                }

            }

        });

        // 주문담기 버튼 추가 및 설정
        order_bt.setFont(new Font("돋움", Font.PLAIN, 25));
        order_bt.setBounds(1250, 900, 500, 150);
        pn.add(order_bt); // 주문담기 버튼 생성 및 출력
        order_bt.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JButton srcBtn = (JButton) e.getSource();
                if (srcBtn == order_bt) {
                    int result = JOptionPane.showConfirmDialog(order_bt, "주문하시겠습니까?", "주문확인",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (result == JOptionPane.OK_OPTION) {

                        Custmoer_Order();
                        // 주문담기 버튼을 누르면 주문내역,총 주문비용 을 모두 지우고 다시 주문문구 생성
                        ordertxt.setText("");
                        listtxt.append(KioskSystem.total_Price());
                        ordertxt.append("<장바구니> \n\n");
                        KioskSystem.order.removeAllElements();
                        KioskSystem.price.removeAllElements();

                    }
                }

            }

            // 주문리스트에 저장할 내용을 설정
            private void Custmoer_Order() {

                Menu m;
                listtxt.append("-------------------------- 주문 리스트 --------------------------\n");

                // 모든 주문리스트를 불러옴
                for (int i = 0; i < KioskSystem.getNumOrders(); i++) {
                    m = KioskSystem.getOrder(i);
                    listtxt.append(i + 1 + ". ");
                    listtxt.append(m.toString());

                }

                listtxt.append("\n--------------------------------------------------------------------\n");
            }

        });

        // 첫 화면에는 주문기능 보이지 않게하기
        ordertxt.setVisible(false);
        order_bt.setVisible(false);

        // 프레임 설정
        fr.setLocation(0, 0);
        fr.setContentPane(pn);
        fr.setSize(1900, 1200);
        fr.setVisible(true);
        fr.setResizable(false);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
