import {createRouter, createWebHistory} from "vue-router";
import FAQ from "@/components/faq/FAQ";
import MainPage from "@/components/main/MainPage";
import FacultyTable from "@/components/entities/faculty/FacultyTable";
import DepartmentTable from "@/components/entities/department/DepartmentTable";
import TeacherTable from "@/components/entities/teacher/TeacherTable";
import StudentTable from "@/components/entities/student/StudentTable";
import DisciplineTable from "@/components/entities/discipline/DisciplineTable";
import GroupTable from "@/components/entities/group/GroupTable";
import ScheduleTable from "@/components/entities/schedule/ScheduleTable";
import Test from "@/components/tests/Test";
import DepartmentForm from "@/components/entities/department/DepartmentForm";
import FacultyForm from "@/components/entities/faculty/FacultyForm";
import DisciplineForm from "@/components/entities/discipline/DisciplineForm";
import TeacherForm from "@/components/entities/teacher/TeacherForm";
import StudentForm from "@/components/entities/student/StudentForm";
import GroupForm from "@/components/entities/group/GroupForm";
import ScheduleForm from "@/components/entities/schedule/ScheduleForm";
import SuccessPage from "@/components/results/SuccessPage";
import NotFoundPage from "@/components/results/NotFoundPage";
import InternalServerErrorPage from "@/components/results/InternalServerErrorPage";
import MethodNotAllowed from "@/components/results/MethodNotAllowed";

export default createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: "/",
            name: "main",
            component: MainPage,
        },
        {
            path: '/faculties',
            component: FacultyTable,
            name: "getFaculties"
        },
        {
            path: "/faculties",
            component: FacultyForm,
            name: "addFaculty"
        },
        {
            path: "/faculties/:id", // must be changed
            component: FacultyForm,
            name: "updateFaculty"
        },
        {
            path: "/departments",
            component: DepartmentTable,
            name: "getDepartments"
        },
        {
            path: "/departments",
            component: DepartmentForm,
            name: "addDepartment"
        },
        {
            path: "/departments/:id",
            component: DepartmentForm,
            name: "updateDepartment"
        },
        {
            path: "/teachers",
            component: TeacherTable,
            name: "getTeachers"
        },
        {
            path: "/teachers",
            component: TeacherForm,
            name: "addTeacher"
        },
        {
            path: "/teachers/:id",
            component: TeacherForm,
            name: "updateTeacher"
        },
        {
            path: "/students",
            component: StudentTable,
            name: "getStudents"
        },
        {
            path: "/students",
            component: StudentForm,
            name: "addStudent"
        },
        {
            path: "/students/:id",
            component: StudentForm,
            name: "updateStudent"
        },
        {
            path: "/disciplines",
            component: DisciplineTable,
            name: "getDisciplines"
        },
        {
            path: "/disciplines/",
            component: DisciplineForm,
            name: "addDiscipline"
        },
        {
            path: "/disciplines/:id",
            component: DisciplineForm,
            name: "updateDiscipline"
        },
        {
            path: "/groups",
            component: GroupTable,
            name: "getGroups"
        },
        {
            path: "/groups",
            component: GroupForm,
            name: "addGroup"
        },
        {
            path: "/groups/:id",
            component: GroupForm,
            name: "updateGroup"
        },
        //must improve schedule
        {
            path: "/schedules",
            component: ScheduleTable,
            name: "getSchedules"
        },
        {
            path: "/schedules",
            component: ScheduleForm,
            name: "addSchedule"
        },
        {
            path: "/schedules/:id",
            component: ScheduleForm,
            name: "updateSchedule"
        },
        {
            path: "/faq",
            name: "faq",
            component: FAQ
        },
        {
            path: "/success",
            component: SuccessPage,
            name: "successPage"
        },
        {
            path: "/:pathMatch(.*)*",
            component: NotFoundPage,
            name: "notFound"
        },
        {
            path: "/test",
            component: Test,
        },
        {
            path: "/method-error",
            component: MethodNotAllowed,
            name: "methodNotAllowed"
        },
        {
            path: "/server-error",
            component: InternalServerErrorPage,
            name: "serverErrorPage"
        }
    ]
})