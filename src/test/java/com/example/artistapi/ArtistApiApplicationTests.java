package com.example.artistapi;

import com.example.artistapi.controllers.Controller;

import static org.assertj.core.api.Assertions.assertThat;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ArtistApiApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private Controller controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

	// @Test
	public void shouldReturnNormal() throws Exception {
		String expectedContent = "{\"mbid\":\"f27ec8db-af05-4f36-916e-3d57f91ecf5e\",\"description\":\"<link rel=\\\"mw-deduplicated-inline-style\\\" href=\\\"mw-data:TemplateStyles:r1033289096\\\">\\n<p class=\\\"mw-empty-elt\\\">\\n\\n\\n\\n</p>\\n<p><b>Michael Joseph Jackson</b> (August 29, 1958 â?? June 25, 2009) was an American singer, songwriter and dancer. Dubbed the \\\"King of Pop\\\", he is regarded as one of the most significant cultural figures of the 20th century. Over a four-decade career, his contributions to music, dance and fashion, along with his publicized personal life, made him a global figure in popular culture. Jackson influenced artists across many music genres; through stage and video performances, he popularized complicated dance moves such as the moonwalk, to which he gave the name, as well as the robot. He is the most awarded music artist in history.\\n</p><p>The eighth child of the Jackson family, Jackson made his professional debut in 1964 with his elder brothers Jackie, Tito, Jermaine and Marlon as a member of the Jackson 5 (later known as the Jacksons). Jackson began his solo career in 1971 while at Motown Records. He became a solo star with his 1979 album <i>Off the Wall</i>. His music videos, including those for \\\"Beat It\\\", \\\"Billie Jean\\\" and \\\"Thriller\\\" from his 1982 album <i>Thriller</i>, are credited with breaking racial barriers and transforming the medium into an artform and promotional tool. He helped propel the success of MTV and continued to innovate with videos for the albums <i>Bad</i> (1987), <i>Dangerous</i> (1991) and <i>HIStory: Past, Present and Future, Book I</i> (1995). <i>Thriller</i> became the best-selling album of all time, while <i>Bad</i> was the first album to produce five U.S. <i>Billboard</i> Hot 100 number-one singles.</p><p>From the late 1980s, Jackson became a figure of controversy and speculation due to his changing appearance, relationships, behavior and lifestyle. In 1993, he was accused of sexually abusing the child of a family friend. The lawsuit was settled out of civil court; Jackson was not indicted due to lack of evidence. In 2005, he was tried and acquitted of further child sexual abuse allegations and several other charges. In both cases, the FBI found no evidence of criminal conduct on Jackson's behalf in either case. In 2009, while preparing for a series of comeback concerts, This Is It, Jackson died from an overdose of propofol administered by his personal physician, Conrad Murray, who was convicted in 2011 of involuntary manslaughter.\\n</p><p>Jackson is one of the best-selling music artists of all time, with estimated sales of over 400<span>Â </span>million records worldwide. He had 13 <i>Billboard</i> Hot 100 number-one singles (more than any other male artist in the Hot 100 era) and was the first artist to have a top-ten single in the <i>Billboard</i> Hot 100 in five different decades. His honors include 15 Grammy Awards, 6 Brit Awards, a Golden Globe Award and 39 Guinness World Records, including the \\\"Most Successful Entertainer of All Time\\\". Jackson's inductions include the Rock and Roll Hall of Fame (twice), the Vocal Group Hall of Fame, the Songwriters Hall of Fame, the Dance Hall of Fame (the only recording artist to be inducted) and the Rhythm and Blues Music Hall of Fame. In 2016, his estate earned $825<span>Â </span>million, the highest yearly amount for a celebrity ever recorded by <i>Forbes</i>.\\n</p>\",\"albums\":[{\"title\":\"Got to Be There\",\"id\":\"97e0014d-a267-33a0-a868-bb4e2552918a\",\"image\":\"http://coverartarchive.org/release/7d65853b-d547-4885-86a6-51df4005768c/1619682960.jpg\"},{\"title\":\"Ben\",\"id\":\"51343255-0ad3-3635-9aa2-548ba939b23e\",\"image\":\"http://coverartarchive.org/release/cf81f5db-6b4d-493b-8f8f-c0f8c51442f9/11670488852.jpg\"},{\"title\":\"Music & Me\",\"id\":\"06b064b9-01e7-32d8-b585-86404584e795\",\"image\":\"http://coverartarchive.org/release/7c73f72d-8fa2-45a7-9125-a04696f64f3a/1620517729.jpg\"},{\"title\":\"Forever, Michael\",\"id\":\"50b9d7de-9124-33c0-83a3-76722bf346e5\",\"image\":\"http://coverartarchive.org/release/3fdd7c32-2da8-480c-8b70-1c628a7fd009/1619702784.jpg\"},{\"title\":\"Off the Wall\",\"id\":\"ee749c63-5699-38e0-b565-7e84414648d9\",\"image\":\"http://coverartarchive.org/release/6258e39d-bef4-4d5a-a654-440cf4c4c29a/5349015874.jpg\"},{\"title\":\"One Day In Your Life\",\"id\":\"c6f67aff-c2a9-4d29-ab71-9d740c06e343\",\"image\":\"http://coverartarchive.org/release/f038c12e-3ff2-4676-b51a-93f168edb50c/31149177185.jpg\"},{\"title\":\"Thriller\",\"id\":\"f32fab67-77dd-3937-addc-9062e28e4c37\",\"image\":\"http://coverartarchive.org/release/e1b94ba6-c63c-4c2d-8928-9d1a525b7000/22018478497.jpg\"},{\"title\":\"Bad\",\"id\":\"a5711a77-42d1-3f4c-830c-e27a96f0800f\",\"image\":\"http://coverartarchive.org/release/60b529f1-f99b-499f-9b3d-e96f9971039e/17067123637.jpg\"},{\"title\":\"Dangerous\",\"id\":\"d6b52521-0dfa-390f-970f-790174c22752\",\"image\":\"http://coverartarchive.org/release/45417dcf-d97a-4f36-b729-441846bda882/8294209818.jpg\"},{\"title\":\"HIStory: Past, Present and Future, Book I\",\"id\":\"2324e560-e8ba-302d-a43d-2ea5ec9c83f7\",\"image\":\"http://coverartarchive.org/release/69b4db43-519d-4621-a72f-30f8d30c2158/8316263548.jpg\"},{\"title\":\"Invincible\",\"id\":\"c24c5313-da47-3155-8277-a6a1a4237966\",\"image\":\"http://coverartarchive.org/release/17fb437a-437f-3153-8bc6-3e6135032d03/1613909539.jpg\"},{\"title\":\"The Best of Michael Jackson\",\"id\":\"90915175-cc35-3970-99f5-8f279ab59585\",\"image\":\"http://coverartarchive.org/release/56274aa3-457e-49b6-ad61-46df84cef737/18821116150.jpg\"},{\"title\":\"One Day in Your Life\",\"id\":\"0621bd78-b867-39ab-8606-9636bfd94447\",\"image\":\"http://coverartarchive.org/release/264039fe-1253-4aa5-baae-406163c733b5/1395290002.jpg\"},{\"title\":\"14 Greatest Hits With the Jackson 5\",\"id\":\"22fc8d45-6802-46f8-8a78-6ae823a9ed92\",\"image\":\"http://coverartarchive.org/release/f1c43fd5-066d-462a-93fa-33d7bb23564d/1613886666.jpg\"},{\"title\":\"18 Greatest Hits\",\"id\":\"ffc3f8b5-7b22-40ed-8867-0cad52b6b2ae\",\"image\":\"http://coverartarchive.org/release/75276adf-7504-4bba-8630-631ef020c31b/1871938266.jpg\"},{\"title\":\"18 Greatest Hits\",\"id\":\"6f33ff5d-025a-42d6-827e-6d5bb5a30b4a\",\"image\":\"http://coverartarchive.org/release/6402fbc9-cb27-4306-96eb-10e6dc489aaf/12592766269.jpg\"},{\"title\":\"Great Songs and Performances That Inspired the Motown 25th Anniversary Television Special\",\"id\":\"e6696f23-a356-4cff-a096-fdf2a1e1a358\",\"image\":\"http://coverartarchive.org/release/d77fe3d8-8a4b-4849-87d3-dabfb9f75e44/1619672416.jpg\"},{\"title\":\"Farewell My Summer Love\",\"id\":\"500d9b05-68c3-3535-86e3-cf685869efc0\",\"image\":\"http://coverartarchive.org/release/8172928a-a6c7-4d7c-83c8-5db2a4575094/13404446094.jpg\"},{\"title\":\"Got to Be There / Ben\",\"id\":\"b513c135-b957-305b-9c5c-7f829d6195b3\",\"image\":\"http://coverartarchive.org/release/f20ad013-43c9-4f91-9a23-741f5c6cff6a/15444575030.jpg\"},{\"title\":\"Anthology\",\"id\":\"37906983-1005-36fb-b8e7-3a04687e6f4f\",\"image\":\"http://coverartarchive.org/release/a7a74484-8c25-47e3-9afc-7de701ad3dde/1619836290.jpg\"},{\"title\":\"The 12â?³ Tape\",\"id\":\"6a427340-2d07-45b5-b557-aaaab91357fd\",\"image\":\"http://coverartarchive.org/release/9d647919-ce5e-46ea-92bd-5d87c5b82012/28872180093.jpg\"},{\"title\":\"Love Songs\",\"id\":\"5baedc41-91da-44c9-8289-6619a853e635\",\"image\":\"http://coverartarchive.org/release/f91ef9f3-f203-4951-af9a-4e1fa20a1f0d/1619530618.jpg\"},{\"title\":\"The Michael Jackson Mix\",\"id\":\"60005e6f-2299-3a22-a928-e8002b91e834\",\"image\":\"http://coverartarchive.org/release/f9ede3a4-95e3-44a2-8502-53b8992fdf70/1620171628.jpg\"},{\"title\":\"Instrumental Version Collection\",\"id\":\"f674b393-2e2a-3008-aca3-2f5a115ebe31\",\"image\":\"http://coverartarchive.org/release/044a3fdc-b591-4776-acef-bb3dc70b70cb/1619551610.jpg\"},{\"title\":\"The Original Soul of Michael Jackson\",\"id\":\"30abac22-0c88-3340-b955-61dd3be73c55\",\"image\":\"http://coverartarchive.org/release/7aad4ef7-06e9-4a6e-8ae2-205a568c7ca6/1513880900.jpg\"}]}";

		this.mockMvc.perform(get("/f27ec8db-af05-4f36-916e-3d57f91ecf5e")).andDo(print())
				.andExpect(content().string(containsString(expectedContent)));
	}

	@Test
	public void shouldReturnErrorMessage() throws Exception {
		String expectedContent = "{\"mbid\":\"5a\",\"description\":\"No artist found for MBID: 5a. Try with '5b11f4ce-a62d-471e-81fc-a69a8278c7da' or '070d193a-845c-479f-980e-bef15710653e' instead :)\",\"albums\":null}";
		this.mockMvc.perform(get("/5a")).andDo(print())
				.andExpect(content().string(containsString(expectedContent)));
	}

	//@Test
	public void shouldReturnErrorMessage2() throws Exception {
		String expectedContent = "{\"info\":\"Please provide an MBID in the url! For example: /070d193a-845c-479f-980e-bef15710653e\"}";
		this.mockMvc.perform(get("/")).andDo(print())
				.andExpect(content().string(containsString(expectedContent)));
	}
}
