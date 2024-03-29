
package com.techacademy.kadaijavareview05;

//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;

public class Review05 {

    public static void myDelete(int id) {

        try {
            // JDBCドライバをロード
            Class.forName("com.mysql.cj.jdbc.Driver");

            // キー入力を読み取とって変数idに代入
            // System.out.print("IDを入力してください > ");
            // int id = keyIn();

            // SQL文ひな形（?は変数idが代入される）
            String sql = "Delete FROM user WHERE id = ? limit 1";

            try (
                    // DB接続
                    Connection con = DriverManager.getConnection(
                            //"jdbc:mysql://localhost/kadaidb?useSSL=false&allowPublicKeyRetrieval=true",
                            //"root",
                            //"rootroot");
                            "jdbc:mysql://localhost/tutorial_develop?useSSL=false&allowPublicKeyRetrieval=true",
                            "devuser",
                            "password");
                    PreparedStatement pstmt = con.prepareStatement(sql);
                    ) {

                // SQLの1つ目の?にidを代入
                pstmt.setInt(1, id);
                
                //deleteSQL呼び出し
                pstmt.executeUpdate();
                System.out.println("myDeleteメソッド完了");

                // try (
                        // SQLを実行
                        // int rs = pstmt.executeQuery();
                        // ) {
                    // 実行結果を取得
                    // rs.next();
                    // String name = rs.getString("Name");
                    // int age = rs.getInt("age");

                    // 取得した値を表示
                    // System.out.println(name);
                    // System.out.println(age);
                // }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            // JDBCドライバをロードに失敗した場合
            e.printStackTrace();
        }
    }

    // private static int keyIn() {
        // String line = null;
        // try {
            // BufferedReader key = new BufferedReader(new InputStreamReader(System.in));
            // line = key.readLine();
        // } catch (IOException e) {
        // }
        // return Integer.parseInt(line);
    // }

}