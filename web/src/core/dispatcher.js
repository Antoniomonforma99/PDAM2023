import useAuthStore from '@/stores/authStore.js';
import router from '@/routes/index.js';

export const initDispatch = () => {
    const authStore = useAuthStore();

    if (authStore.isLoggedIn) {
        //router.push('/dashboard');
    } else {
        router.push('/auth/login');
    }
};
