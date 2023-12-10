import { defineStore } from 'pinia';
import router from '@/routes/index.js';
import axios from 'axios';
import { statusCodes } from '../helpers/constants.js';

export default defineStore('auth', {
    state: () => ({
        user: JSON.parse(localStorage.getItem('user'))
    }),

    getters: {
        isLoggedIn: (state) => {
            if (state.user !== null) {
                if (state.user.rememberMe) {
                    return true;
                } else {
                    state.logout();
                    return false;
                }
            } else {
                return false;
            }
        },
        getJWT: (state) => {
            return state.user?.jwt;
        }
    },

    actions: {
        async login(email, password, rememberMe) {

            
            const url = import.meta.env.VITE_VUE_APP_API_URL + '/auth/login/admin';
            const authParams = {
                username: email,
                password: password
            };

            let success = 0;
            let user = undefined;

            await axios
                .post(url, authParams)
                .then((res) => {          
                    user = {
                        id: res.data.id,
                        email: res.data.email,
                        name: res.data.name,
                        roles : res.data.roles,
                        createdAt : res.data.createdAt,
                        token : res.data.token,
                        refreshToken: res.data.refreshToken,
                        rememberMe
                    };

                    console.log("holaa" +user.id);

                    localStorage.setItem('user', JSON.stringify(user));
                    this.setUser(user);

                    success = 1;


                    // if (res.data.statusCode === statusCodes.SUCCESS) {
                    //     const userResponse = res.data.data;

                    //     user = {
                    //         id: userResponse.id,
                    //         email: userResponse.email,
                    //         name: userResponse.name,
                    //         roles : userResponse.roles,
                    //         createdAt : userResponse.createdAt,
                    //         token : userResponse.token,
                    //         refreshToken: userResponse.refreshToken,
                    //         rememberMe
                    //     };

                    //     localStorage.setItem('user', JSON.stringify(user));
                    //     this.setUser(user);

                    //     success = 1;
                    // }
                })
                .catch((error) => {
                    //console.log(error);
                    // if (error.response.data.statusCode === statusCodes.MISSING_PARAMS) {
                    //     success = -2;
                    // } else if (error.response.data.statusCode === statusCodes.USER_NOT_FOUND) {
                    //     success = -1;
                    // }
                    success = -1
                });

            return success;
        },

        setUser(user) {
            this.user = user;
        },

        logout($sessionExpired = false) {
            this.user = null;
            localStorage.removeItem('user');

            if ($sessionExpired) {
                localStorage.setItem('sessionExpiredAlert', 1);
            }

            router.push('/auth/login');
        }
    }
});
