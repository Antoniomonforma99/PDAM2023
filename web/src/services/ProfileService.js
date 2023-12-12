import useAuthStore from '@/stores/authStore.js';
import axios from 'axios';
import { statusCodes } from '../helpers/constants.js';

export default class ProfileService {
    async getData() {
        const authStore = useAuthStore;

        const url = import.meta.env.VITE_VUE_APP_API_URL + '/me';


        const user = JSON.parse(localStorage.getItem('user'));

        const token = user.token;

        const authParams = {
            headers: {
                Authorization: `Bearer ${token}`
            }
        };

        let success = 0;

        let profile = {};

        await axios
            .get(url, authParams)
            .then((res) => {
                success = 1
                profile = res.data;
            })
            .catch((error) => {
                console.log(error);
                if (error.response?.data.statusCode === statusCodes.MISSING_PARAMS) {
                    success = -2;
                } else if (error.response?.data.statusCode === statusCodes.USER_NOT_FOUND) {
                    success = -1;
                } else if (error.response?.data.statusCode === statusCodes.UNAUTHORIZED) {
                    $sessionExpired = true;
                    authStore.logout($sessionExpired);
                } else {
                    console.log(error);
                }
            });

        return profile;

    }

    async updatePassword(changePasswordRequestDTO) {
        const authStore = useAuthStore;

        const url = import.meta.env.VITE_VUE_APP_API_URL + '/user/changePassword';


        const user = JSON.parse(localStorage.getItem('user'));

        const token = user.token;

        const authParams = {
            headers: {
                Authorization: `Bearer ${token}`
            }
        };

        let success = 0;

        let profile = {};

        let formData = new FormData();
        formData.append('body', new Blob([JSON.stringify(changePasswordRequestDTO)], {
            type: "application/json"
        }));


        await axios
            .put(url, changePasswordRequestDTO , authParams)
            .then((res) => {
                success = 1
                profile = res.data;
                console.log("aafff" +res.data)
            })
            .catch((error) => {
                console.log(error);
                if (error.response?.data.statusCode === statusCodes.MISSING_PARAMS) {
                    success = -2;
                } else if (error.response?.data.statusCode === statusCodes.USER_NOT_FOUND) {
                    success = -1;
                } else if (error.response?.data.statusCode === statusCodes.UNAUTHORIZED) {
                    $sessionExpired = true;
                    authStore.logout($sessionExpired);
                } else {
                    console.log(error);
                }
            });

        return profile;
    }

    async createUser(createUserRequestDTO) {
        const authStore = useAuthStore;

        const url = import.meta.env.VITE_VUE_APP_API_URL + '/auth/register/admin';


        const user = JSON.parse(localStorage.getItem('user'));

        const token = user.token;

        const authParams = {
            headers: {
                Authorization: `Bearer ${token}`
            }
        };

        let success = 0;

        let profile = {};

        let formData = new FormData();
        formData.append('body', new Blob([JSON.stringify(createUserRequestDTO)], {
            type: "application/json"
        }));




        await axios
            .post(url, createUserRequestDTO , authParams)
            .then((res) => {
                success = 1
                profile = res.data;
                console.log("aafff" +res.data)
            })
            .catch((error) => {
                console.log(error);
                if (error.response?.data.statusCode === statusCodes.MISSING_PARAMS) {
                    success = -2;
                } else if (error.response?.data.statusCode === statusCodes.USER_NOT_FOUND) {
                    success = -1;
                } else if (error.response?.data.statusCode === statusCodes.UNAUTHORIZED) {
                    $sessionExpired = true;
                    authStore.logout($sessionExpired);
                } else {
                    console.log(error);
                }
            });

        return profile;
    }
}