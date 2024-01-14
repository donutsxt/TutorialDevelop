package com.techacademy.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;  //テストの前にこの処理を実行。本プロジェクトではSpringSecurityを有効化。
import org.junit.jupiter.api.DisplayName; //JUnitビューに表示されるテスト名を設定。
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith; //コンストラクタ・インジェクションを行なうために必要
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest; //SpringBootを再起動してdata.SQLなど実行
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc; //MockMvcの設定、テストコード上で「常に正しいレスポンスを返すモック」に置き換え
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.techacademy.entity.User;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    private MockMvc mockMvc;

    private final WebApplicationContext webApplicationContext;

    UserControllerTest(WebApplicationContext context) {
        this.webApplicationContext = context;
    }

    @BeforeEach
    void beforeEach() {
        // Spring Securityを有効にする
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity()).build();
    }

    @Test
    @DisplayName("User更新画面")
    @WithMockUser
    void testGetUser() throws Exception {
        // HTTPリクエストに対するレスポンスの検証
        MvcResult result = mockMvc.perform(get("/user/update/1/")) // mockMvcを使いURLにアクセス
            .andExpect(status().isOk()) // レスポンスのステータスが「200 OK」であることを確認
            .andExpect(model().attributeExists("user")) // viewに渡しているModelにuserが登録されていることを確認
            .andExpect(model().hasNoErrors()) // Modelにエラーが無いことを確認
            .andExpect(view().name("user/update")) // viewの名称を確認
            .andReturn(); // 内容を取得してresult変数に格納

        // userの検証
        // Modelからuserを取り出す
        User user = (User)result.getModelAndView().getModel().get("user");
        assertEquals(user.getId(), 1);
        assertEquals(user.getName(), "キラメキ太郎");
    }

    @Test
    @DisplayName("User一覧画面")
    @WithMockUser
    void testGetList() throws Exception {
        // HTTPリクエストに対するレスポンスの検証
        MvcResult result = mockMvc.perform(get("/user/list")) // mockMvcを使いURLにアクセス
            .andExpect(status().isOk()) // レスポンスのステータスが「200 OK」であることを確認
            .andExpect(model().attributeExists("userlist")) // viewに渡しているModelにuserが登録されていることを確認
            .andExpect(model().hasNoErrors()) // Modelにエラーが無いことを確認
            .andExpect(view().name("user/list")) // viewの名称を確認
            .andReturn(); // 内容を取得してresult変数に格納

        // userの検証
        // Modelからuserlistを取り出す
//        User user = (User)result.getModelAndView().getModel().get("userlist");
//        assertEquals(user.getId(), 1);
//        assertEquals(user.getName(), "キラメキ太郎");
        @SuppressWarnings({ "unchecked", "unused" })
        List<User> userlist = (List<User>)result.getModelAndView().getModel().get("userlist");
        System.out.println(userlist.size());
        assertEquals(userlist.size(),3);
//        for(int i = 0; i < userlist.size(); i++) {
            assertEquals(userlist.get(0).getId(), 1);
            assertEquals(userlist.get(0).getName(), "キラメキ太郎");
            assertEquals(userlist.get(1).getId(), 2);
            assertEquals(userlist.get(1).getName(), "キラメキ次郎");
            assertEquals(userlist.get(2).getId(), 3);
            assertEquals(userlist.get(2).getName(), "キラメキ花子");
//        }
    }
}