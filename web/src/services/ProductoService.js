import useAuthStore from '@/stores/authStore.js';
import axios from 'axios';
import { statusCodes } from '../helpers/constants.js';

export default class ProductoService {

    async getAllProductos() {
        const authStore = useAuthStore;

        const url = import.meta.env.VITE_VUE_APP_API_URL + '/producto/';


        const user = JSON.parse(localStorage.getItem('user'));

        const token = user.token;

        

        const authParams = {
            headers: {
                Authorization: `Bearer ${token}`
            }
        };

        let success = 0;
        let productos = [];

        

        await axios
            .get(url, authParams)
            .then((res) => {
                    success = 1;   
                    productos = res.data.contenido;

                    

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


            return productos;
            
    }


}