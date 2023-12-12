
import { createRouter, createWebHistory } from 'vue-router';
import AppLayout from '@/layout/AppLayout.vue';

const router = createRouter({
    history: createWebHistory('/admin'),
    routes : [
        {
            path : '/',
            component : AppLayout,
            children : [
                {
                    path : '/dashboard',
                    name : 'dashboard',
                    component : () => import('@/views/pages/Dashboard.vue')
                },
                {
                    path : '/me',
                    name: 'profile',
                    component : () => import('@/views/pages/auth/Profile.vue')
                },
                {
                    path : '/productos',
                    name : 'productos-list',
                    component : () => import('@/views/pages/productos/Productos-list.vue')
                },
                {
                    path : '/productos/new',
                    name : 'producto-new',
                    component : () => import('@/views/pages/productos/Producto-new.vue')
                },
                {
                    path : '/productos/:idProducto',
                    name : 'producto-detail',
                    component : () => import('@/views/pages/productos/Producto-detail.vue')
                },
                {
                    path : '/menus',
                    name : 'menus-list',
                    component : () => import('@/views/pages/menus/Menus-list.vue')
                },
                {
                    path : '/categorias',
                    name : 'categorias-list',
                    component : () => import('@/views/pages/categorias/Categorias-list.vue')
                },
                {
                    path : '/categorias/new',
                    name : 'categoria-new',
                    component : () => import('@/views/pages/categorias/Categoria-new.vue')
                },
                {
                    path : '/personal',
                    name : 'personal-list',
                    component : () => import('@/views/pages/personal/Personal-list.vue')
                },
                {
                    path : '/personal/nuevoUsuario',
                    name : 'personal-new',
                    component : () => import('@/views/pages/personal/Nuevo-usuario.vue')
                },
            ]
        },

        {
            path: '/auth/login',
            name: 'login',
            component: () => import('@/views/pages/auth/Login.vue')
        },
        {
            path: '/auth/access',
            name: 'accessDenied',
            component: () => import('@/views/pages/auth/Access.vue')
        },
        {
            path: '/auth/error',
            name: 'error',
            component: () => import('@/views/pages/auth/Error.vue')
        }
    ]
});

export default router;

