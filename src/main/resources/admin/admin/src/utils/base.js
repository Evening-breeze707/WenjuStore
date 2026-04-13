const base = {
    get() {
        return {
            url : "http://localhost:8080/springbootpkptt/",
            name: "springbootpkptt",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/springbootpkptt/front/dist/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "基于Spring Boot的文具销售系统的设计与实现"
        } 
    }
}
export default base
