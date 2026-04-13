import VueRouter from 'vue-router'

//引入组件
import Index from '../pages'
import Home from '../pages/home/home'
import Login from '../pages/login/login'
import Register from '../pages/register/register'
import Center from '../pages/center/center'
import Messages from '../pages/messages/list'
import Storeup from '../pages/storeup/list'
import AddrList from '../pages/shop-address/list'
import AddrAdd from '../pages/shop-address/addOrUpdate'
import Order from '../pages/shop-order/list'
import OrderConfirm from '../pages/shop-order/confirm'
import Cart from '../pages/shop-cart/list'
import News from '../pages/news/news-list'
import NewsDetail from '../pages/news/news-detail'
import yonghuList from '../pages/yonghu/list'
import yonghuDetail from '../pages/yonghu/detail'
import yonghuAdd from '../pages/yonghu/add'
import wenjufenleiList from '../pages/wenjufenlei/list'
import wenjufenleiDetail from '../pages/wenjufenlei/detail'
import wenjufenleiAdd from '../pages/wenjufenlei/add'
import wenjuxinxiList from '../pages/wenjuxinxi/list'
import wenjuxinxiDetail from '../pages/wenjuxinxi/detail'
import wenjuxinxiAdd from '../pages/wenjuxinxi/add'
import yuleshangpinList from '../pages/yuleshangpin/list'
import yuleshangpinDetail from '../pages/yuleshangpin/detail'
import yuleshangpinAdd from '../pages/yuleshangpin/add'
import aboutusList from '../pages/aboutus/list'
import aboutusDetail from '../pages/aboutus/detail'
import aboutusAdd from '../pages/aboutus/add'

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}

//配置路由
export default new VueRouter({
	routes:[
		{
      path: '/',
      redirect: '/index/home'
    },
		{
			path: '/index',
			component: Index,
			children:[
				{
					path: 'home',
					component: Home
				},
				{
					path: 'center',
					component: Center,
				},
				{
					path: 'messages',
					component: Messages
				},
				{
					path: 'storeup',
					component: Storeup
				},
                {
                    path: 'shop-address/list',
                    component: AddrList
                },
                {
                    path: 'shop-address/addOrUpdate',
                    component: AddrAdd
                },
				{
					path: 'shop-order/order',
					component: Order
				},
				{
					path: 'cart',
					component: Cart
				},
				{
					path: 'shop-order/orderConfirm',
					component: OrderConfirm
				},
				{
					path: 'news',
					component: News
				},
				{
					path: 'newsDetail',
					component: NewsDetail
				},
				{
					path: 'yonghu',
					component: yonghuList
				},
				{
					path: 'yonghuDetail',
					component: yonghuDetail
				},
				{
					path: 'yonghuAdd',
					component: yonghuAdd
				},
				{
					path: 'wenjufenlei',
					component: wenjufenleiList
				},
				{
					path: 'wenjufenleiDetail',
					component: wenjufenleiDetail
				},
				{
					path: 'wenjufenleiAdd',
					component: wenjufenleiAdd
				},
				{
					path: 'wenjuxinxi',
					component: wenjuxinxiList
				},
				{
					path: 'wenjuxinxiDetail',
					component: wenjuxinxiDetail
				},
				{
					path: 'wenjuxinxiAdd',
					component: wenjuxinxiAdd
				},
				{
					path: 'yuleshangpin',
					component: yuleshangpinList
				},
				{
					path: 'yuleshangpinDetail',
					component: yuleshangpinDetail
				},
				{
					path: 'yuleshangpinAdd',
					component: yuleshangpinAdd
				},
				{
					path: 'aboutus',
					component: aboutusList
				},
				{
					path: 'aboutusDetail',
					component: aboutusDetail
				},
				{
					path: 'aboutusAdd',
					component: aboutusAdd
				},
			]
		},
		{
			path: '/login',
			component: Login
		},
		{
			path: '/register',
			component: Register
		},
	]
})
