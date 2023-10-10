package com.example.FlapKap.s_project;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@SpringBootTest
class ApplicationTests {
   Calculator underTest=new Calculator();
	@Test
	void itShouldTwoAddNumbers() {
		//given
		int numberOne=20;
		int numberTwo=30;
		//when
		int result = underTest.add(numberTwo,(numberOne));
		//then
		assertThat(result).isEqualTo(50);

	}

	class Calculator{
		int add(int a,int b){
			return a+b;
		}
	}

}
