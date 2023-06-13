package com.example.rozklad.service.schedule;

import com.example.rozklad.domain.Discipline;
import com.example.rozklad.domain.Group;
import com.example.rozklad.domain.Schedule;
import com.example.rozklad.domain.Teacher;
import com.example.rozklad.exception.EntityNotFoundException;
import com.example.rozklad.repository.DisciplineRepository;
import com.example.rozklad.repository.GroupRepository;
import com.example.rozklad.repository.ScheduleRepository;
import com.example.rozklad.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final GroupRepository groupRepository;
    private final TeacherRepository teacherRepository;
    private final DisciplineRepository disciplineRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, GroupRepository groupRepository, TeacherRepository teacherRepository, DisciplineRepository disciplineRepository) {
        this.scheduleRepository = scheduleRepository;
        this.groupRepository = groupRepository;
        this.teacherRepository = teacherRepository;
        this.disciplineRepository = disciplineRepository;
    }

    @Override
    public void add(Map<String, String> scheduleParams) {
        String time = scheduleParams.get("time");
        String classroom = scheduleParams.get("classroom");
        Group group = groupRepository.findById(Long.parseLong(scheduleParams.get("group_id"))).get();
        Teacher teacher = teacherRepository.findById(Long.parseLong(scheduleParams.get("teacher_id"))).get();
        Discipline discipline = disciplineRepository.findById(Long.parseLong(scheduleParams.get("discipline_id"))).get();

        Schedule schedule = new Schedule(teacher, discipline, group, time, classroom);
        scheduleRepository.save(schedule);
    }

    @Override
    public void update(Long id, Map<String, String> scheduleParams) {
        Optional<Schedule> oldSchedule = scheduleRepository.findById(id);

        if (oldSchedule.isEmpty()) {
            throw new EntityNotFoundException(Schedule.class, id);
        }

        String time = scheduleParams.get("time");
        String classroom = scheduleParams.get("classroom");
        Group group = groupRepository.findById(Long.parseLong(scheduleParams.get("group_id"))).get();
        Teacher teacher = teacherRepository.findById(Long.parseLong(scheduleParams.get("teacher_id"))).get();
        Discipline discipline = disciplineRepository.findById(Long.parseLong(scheduleParams.get("discipline_id"))).get();


        Schedule updatedSchedule = oldSchedule.get();
        updatedSchedule.setTime(time);
        updatedSchedule.setGroup(group);
        updatedSchedule.setClassroom(classroom);
        updatedSchedule.setDiscipline(discipline);
        updatedSchedule.setTeacher(teacher);
        scheduleRepository.save(updatedSchedule);
    }
}
