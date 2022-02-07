package com.example.han.service;

import com.example.han.domain.*;

/**
 * packageName: com.example.han.service
 * fileName        : StudentServiceImpl.java
 * author          : sungsuhan
 * date            : 2022-02-07
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-02-07         sungsuhan        최초 생성
 **/
public class StudentServiceImpl implements StudentService{
    /**
     * BMI = w / t*t  -> 키 m로 바꿀려면 x10000
     * 고도 비만 : 35 이상
     * 중(重)도 비만 (2단계 비만) : 30 - 34.9
     * 경도 비만 (1단계 비만) : 25 - 29.9
     * 과체중 : 23 - 24.9
     * 정상 : 18.5 - 22.9
     * 저체중 : 18.5 미만
     **/

    @Override
    public String getBmi(BmiDTO param) {
        double bmi = param.getWeight() / (param.getHeight() * param.getHeight() * 10000);
        String s = "";
        if (bmi >= 35) {
            s = "고도 비만";
        }else if (bmi >= 30) {
            s = "중(重)도 비만 (2단계 비만)";
        }else if (bmi >= 25) {
            s = "경도 비만 (1단계 비만)";
        }else if (bmi >= 23) {
            s = "과체중";
        }else if (bmi >= 18.5) {
            s = "정상";
        }else if (bmi <18.5) {
            s = "저체중";
        }
        return String.format("%s",s);
    }

    @Override
    public String calc(CalcDTO calc) {
        int res = 0;

        switch (calc.getOpcode()) {
            case "+":
                res = calc.getNum1() + calc.getNum2();
                break;
            case "-":
                res = calc.getNum1() - calc.getNum2();
                break;
            case "*":
                res = calc.getNum1() * calc.getNum2();
                break;
            case "/":
                res = calc.getNum1() / calc.getNum2();
                break;
        }
        return String.format("%d %s %d = %d", calc.getNum1(), calc.getOpcode(), calc.getNum2(), res);
    }

    @Override
    public String search(GoogleDTO search) {
        return String.format("검색결과 : %s", search.getSearch());
    }

    @Override
    public String getGrade(GradeDTO grade) {
        int total = grade.getKor() + grade.getEng() + grade.getMath();
        int avg = total / 3;
        String pass = (avg >= 60) ? "합격" : "불합격";

        return String.format("########## %s ###########\n" +
                " 이름: %s\n" +
                "> 국어: %d점 \n" +
                "> 영어: %d점 \n" +
                "> 수학: %d점\n" +
                " 총점: %d점 \n" +
                " 평균(정수): %d점\n" +
                " 합격여부: %s\n" +
                "############################", GradeDTO.GRADE_TITLE, grade.getName(), grade.getKor(), grade.getEng(), grade.getMath(), total, avg, pass);
    }

    @Override
    public String login(LoginDTO login) {
        return (login.getPw().equals(login.getPASSWORD())) ? String.format("%s 님의 비번 %s 가 맞습니다, 로그인 성공", login.getName(), login.getPw())
                : String.format("%s 님의 ID는 맞고 비번 %s 가 틀립니다, 로그인 실패", login.getName(), login.getPw());

    }
}
