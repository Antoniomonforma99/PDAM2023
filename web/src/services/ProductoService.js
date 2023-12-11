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

    async createProducto (
         productoRequestDTO, img
    ) {
        const authStore = useAuthStore();
        const url = import.meta.env.VITE_VUE_APP_API_URL +'/producto/new';

        const user = JSON.parse(localStorage.getItem('user'));

        const token = user.token;
        const authParams = {
            headers : {
                "Authorization": `Bearer ${token}`
            }
        };

        let success = 0;

        let formData = new FormData();
        formData.append('file', img);
        formData.append('body', new Blob([JSON.stringify(productoRequestDTO)], {
            type: "application/json"
        }));

        console.log("Awui" +formData)
        

        await axios
            .post(url, formData, authParams
        )
        .then((res) => {
            if(res.data.statusCode === statusCodes.SUCCESS) {
                success = 1;
            }
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
        return success;
    }

    


}