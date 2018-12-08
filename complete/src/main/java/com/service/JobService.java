//package com.service;
//
//import com.model.Job;
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//import java.awt.*;
//import java.util.*;
//import java.util.List;
//
///**
// * Created by z002qz1 on 6/23/18.
// */
//@Service
//@Slf4j
//public class JobService
//{
//    Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    private class CustomComparator implements Comparator<Job>
//    {
//        public int compare(Job job1, Job job2)
//        {
//            Integer j1 = job1.getDuration();
//            Integer j2 = job2.getDuration();
//
//            if(j1==j2)
//            {
//                return job2.getPriority().compareTo(job1.getPriority());
//            }
//
//            if(j1>j2)
//                return 1;
//
//            return -1;
//        }
//    }
//
//
//    private class OppositeComparator implements Comparator<Job>
//    {
//        public int compare(Job job1, Job job2)
//        {
//            Integer j1 = job1.getDuration();
//            Integer j2 = job2.getDuration();
//
//            if(j1==j2)
//            {
//                return job2.getPriority().compareTo(job1.getPriority());
//            }
//
//            if(j1>j2)
//                return -1;
//
//            return 1;
//        }
//    }
//
//    private class FPSComparator implements Comparator<Job>
//    {
//        public int compare(Job job1, Job job2)
//        {
//
//            int tie1=job2.getPriority().compareTo(job1.getPriority());
//
//            if(tie1==0)
//            {
//                int tie2 = job2.getMatchWinner().compareTo(job1.getMatchWinner());
//                if(tie2==0)
//                {
//                    return job2.getDuration().compareTo(job1.getDuration());
//                }
//                return tie2;
//            }
//            return tie1;
//
//        }
//    }
//
//
//    private class EDFComparator implements Comparator<Job>
//    {
//        public int compare(Job job1, Job job2)
//        {
//
////            int tie1=job2.getPriority().compareTo(job1.getPriority());
//
//            Integer j1 = job1.getDeadline();
//            Integer j2 = job2.getDeadline();
//
//            if(j1==j2)
//            {
//                int tie2 = job2.getPriority().compareTo(job1.getPriority());
//                if(tie2==0)
//                {
//                    return job2.getDuration().compareTo(job1.getDuration());
//                }
//            }
//
//            if(j1>j2)
//                return 1;
//
//            return -1;
//
//        }
//
////        public List<Job> sortMod(List<Job> jobList) throws Exception
////        {
////            int i=0,j=0,sorted=0;
////            Job[] asArray  = jobList.toArray(new Job[jobList.size()]);
////
////            for(i=0;i<asArray.length;i++)
////            {
////                for(j=0;j<asArray.length-1-i;j++)
////                {
////                    if(asArray[i].getDeadline()>asArray[j].getDeadline())
////                    {
////                        Job temp = asArray[i];
////                        asArray[i] = asArray[j];
////                        asArray[j] = asArray[i];
////                        Integer soFar=0;
////                        for(int k=0;k<)
////
////                        asArray[i].setDeadline(asArray[i].getDeadline()-);
////                    }
////                }
////            }
////        }
//
//    }
//
//    CustomComparator customComparator = new CustomComparator();
//
//    public List<String> SJFjobScheduler(List<Job> jobList, Integer threadNum) throws Exception
//    {
//
//        List<String>result = new ArrayList<>();
//
//        LinkedHashMap<Integer, List<String>> parseJobs = new LinkedHashMap<>();
//
////        jobList.sort(customComparator);
//
//        Collections.sort(jobList,new CustomComparator());
//
//        logger.info("SJF Jobs list after sorting,{}",jobList);
//        System.out.println("SFJ after sorting"+jobList.toString());
//
//        Integer i=0,j=0,k=0;
//
//        if(threadNum>jobList.size())
//        {
//            for(i=0;i<jobList.size();i++)
//            {   StringBuilder currentThread = new StringBuilder("Thread").append(i).append(" - ").append(jobList.get(i).getName()).append("\t");
//                i++;
//                result.add(currentThread.toString());
//            }
//        }
//        else
//        {
//            i=0;
//            j=0;
//            while(i<jobList.size())
//            {
//                for(j=0;j<threadNum;j++)
//                {
//                    if(parseJobs.get(j)==null&&i<jobList.size())
//                    {
//                        List<String> jobSchedule = new ArrayList<>();
//                        jobSchedule.add(jobList.get(i).getName());
//                        i++;
//                        parseJobs.put(j,jobSchedule);
//                    }
//                    else
//                    {   if(i<jobList.size())
//                        {
//                            List<String> jobSchedule = parseJobs.get(j);
//                            jobSchedule.add(jobList.get(i).getName());
//                            i++;
//                            parseJobs.put(j,jobSchedule);
//                        }
//                    }
//                }
//            }
//
//            for(Map.Entry<Integer,List<String>> entry : parseJobs.entrySet())
//            {
//                Integer threadId = entry.getKey();
//                List<String> assignedJobs = entry.getValue();
//                StringBuilder currentThread = new StringBuilder("Thread").append(threadId).append(" - ").append(assignedJobs.toString()).append("\t");
//                result.add(currentThread.toString());
//            }
//
//
//        }
//        return result;
//    }
//
//
//    public List<String> FCFSJobScheduler(List<Job> jobList, Integer threadNum) throws Exception
//    {
//        List<String>result = new ArrayList<>();
//
//        LinkedHashMap<Integer, List<String>> parseJobs = new LinkedHashMap<>();
//
//
//        logger.info("FCFS Jobs list,{}",jobList);
//        Integer i=0,j=0,k=0;
//
//        if(threadNum>jobList.size())
//        {
//            for(i=0;i<jobList.size();i++)
//            {   StringBuilder currentThread = new StringBuilder("Thread").append(i).append(" - ").append(jobList.get(i).getName()).append("\t");
//                i++;
//                result.add(currentThread.toString());
//            }
//        }
//        else
//        {
//            i=0;
//            j=0;
//            while(i<jobList.size())
//            {
//                for(j=0;j<threadNum;j++)
//                {
//                    if(parseJobs.get(j)==null&&i<jobList.size())
//                    {
//                        List<String> jobSchedule = new ArrayList<>();
//                        jobSchedule.add(jobList.get(i).getName());
//                        i++;
//                        parseJobs.put(j,jobSchedule);
//                    }
//                    else
//                    {   if(i<jobList.size())
//                        {
//                            List<String> jobSchedule = parseJobs.get(j);
//                            jobSchedule.add(jobList.get(i).getName());
//                            i++;
//                            parseJobs.put(j,jobSchedule);
//                        }
//                    }
//                }
//            }
//
//            for(Map.Entry<Integer,List<String>> entry : parseJobs.entrySet())
//            {
//                Integer threadId = entry.getKey();
//                List<String> assignedJobs = entry.getValue();
//                StringBuilder currentThread = new StringBuilder("Thread").append(threadId).append(" - ").append(assignedJobs.toString()).append("\t");
//                result.add(currentThread.toString());
//            }
//
//
//        }
//        return result;
//    }
//
//
//    public List<String> FPSJobScheduler(List<Job> jobList, Integer threadNum) throws Exception
//    {
//
//        List<String>result = new ArrayList<>();
//
//        LinkedHashMap<Integer, List<String>> parseJobs = new LinkedHashMap<>();
//
//        Collections.sort(jobList,new FPSComparator());
//
////        logger.info("Jobs list after sorting,{}",jobList);
//
//        logger.info("FCFS Jobs list,{}",jobList);
//        Integer i=0,j=0,k=0;
//
//        if(threadNum>jobList.size())
//        {
//            for(i=0;i<jobList.size();i++)
//            {   StringBuilder currentThread = new StringBuilder("Thread").append(i).append(" - ").append(jobList.get(i).getName()).append("\t");
//                i++;
//                result.add(currentThread.toString());
//            }
//        }
//        else
//        {
//            i=0;
//            j=0;
//            while(i<jobList.size())
//            {
//                for(j=0;j<threadNum;j++)
//                {
//                    if(parseJobs.get(j)==null&&i<jobList.size())
//                    {
//                        List<String> jobSchedule = new ArrayList<>();
//                        jobSchedule.add(jobList.get(i).getName());
//                        i++;
//                        parseJobs.put(j,jobSchedule);
//                    }
//                    else
//                    {   if(i<jobList.size())
//                    {
//                        List<String> jobSchedule = parseJobs.get(j);
//                        jobSchedule.add(jobList.get(i).getName());
//                        i++;
//                        parseJobs.put(j,jobSchedule);
//                    }
//                    }
//                }
//            }
//
//            //                        (Map.Entry<String, ArrayList<String>> entry : test1.entrySet())
//
//            for(Map.Entry<Integer,List<String>> entry : parseJobs.entrySet())
//            {
//                Integer threadId = entry.getKey();
//                List<String> assignedJobs = entry.getValue();
//                StringBuilder currentThread = new StringBuilder("Thread").append(threadId).append(" - ").append(assignedJobs.toString()).append("\t");
//                result.add(currentThread.toString());
//            }
//
//
//        }
//        return result;
//
//
//    }
//
//
//
//
//
//    public List<String> EDFJobScheduler(List<Job> jobList, Integer threadNum) throws Exception
//    {
//
//        List<String>result = new ArrayList<>();
//
//        LinkedHashMap<Integer, List<String>> parseJobs = new LinkedHashMap<>();
//
//
//        Collections.sort(jobList,new EDFComparator());
//
//        Integer i=0,j=0,k=0;
//
//        if(threadNum>jobList.size())
//        {
//            for(i=0;i<jobList.size();i++)
//            {   StringBuilder currentThread = new StringBuilder("Thread").append(i).append(" - ").append(jobList.get(i).getName()).append("\t");
//                i++;
//                result.add(currentThread.toString());
//            }
//        }
//        else
//        {
//            i=0;
//            j=0;
//            while(i<jobList.size())
//            {
//                for(j=0;j<threadNum;j++)
//                {
//                    if(parseJobs.get(j)==null&&i<jobList.size())
//                    {
//                        List<String> jobSchedule = new ArrayList<>();
//                        jobSchedule.add(jobList.get(i).getName());
//                        i++;
//                        parseJobs.put(j,jobSchedule);
//                    }
//                    else
//                    {   if(i<jobList.size())
//                        {
//                            List<String> jobSchedule = parseJobs.get(j);
//                            jobSchedule.add(jobList.get(i).getName());
//                            i++;
//                            parseJobs.put(j,jobSchedule);
//                        }
//                    }
//                }
//            }
//
//            for(Map.Entry<Integer,List<String>> entry : parseJobs.entrySet())
//            {
//                Integer threadId = entry.getKey();
//                List<String> assignedJobs = entry.getValue();
//                StringBuilder currentThread = new StringBuilder("Thread").append(threadId).append(" - ").append(assignedJobs.toString()).append("\t");
//                result.add(currentThread.toString());
//            }
//
//
//        }
//        return result;
//
//    }
//
//}
