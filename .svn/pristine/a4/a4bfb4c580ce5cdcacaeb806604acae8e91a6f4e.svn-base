/**
 * 用户会话
 */

var SESSION_KEY_USER = "login_user";

export default {

    state: {
        loginUser: JSON.parse(localStorage.getItem(SESSION_KEY_USER))
    },

    getters: {
        hasSession: function (state) {
            return state.loginUser != null;
        },
        //获取会话
        getSession(state) {
            var user = state.loginUser;
            if (user.type == 2) {
                user.name = user.companyName;
            }
            return user;
        }
    },

    mutations: {
        //创建会话
        ['CREATE_SESSION'](state, session) {
            state.loginUser = session;
            localStorage.setItem(SESSION_KEY_USER, JSON.stringify(session));
            console.log('create session');
        },

        //删除会话
        ['REMOVE_SESSION'](state) {
            state.loginUser = null;
            localStorage.removeItem(SESSION_KEY_USER);
            console.log('remove session');
        }
    },

    actions: {

        setSession(context,session) {
            context.commit('CREATE_SESSION',session)
        },

        removeSession(context ) {
            context.commit('REMOVE_SESSION')
        }
    }


};