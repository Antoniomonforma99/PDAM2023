import useAuthStore from '@/stores/authStore.js';
import axios from 'axios';
import { statusCodes } from '../helpers/constants.js';

export default class CategoriaService {

    async getAll() {
        const authStore = useAuthStore;

        const url = import.meta.env.VITE_VUE_APP_API_URL + '/categoria/';


        const user = JSON.parse(localStorage.getItem('user'));

        const token = user.token;

        

        const authParams = {
            headers: {
                Authorization: `Bearer ${token}`
            }
        };

        let success = 0;
        let categorias = [];

        

        await axios
            .get(url, authParams)
            .then((res) => {
                    success = 1;   
                    categorias = res.data;
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


            return categorias;
            
    }

    async create(nombre) {
        const authStore = useAuthStore;

        const url = import.meta.env.VITE_VUE_APP_API_URL + '/categoria/new';


        const user = JSON.parse(localStorage.getItem('user'));

        const token = user.token;

        

        const authParams = {
            headers: {
                Authorization: `Bearer ${token}`
            }
        };

        let success = 0;
        let categoria = [];

        await axios
            .post(url, 
                {
                    nombre : nombre
                },
                authParams
            )
            .then((res) => {
                success = 1;
                 categoria = res.data
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

            return categoria;
        
    }


}