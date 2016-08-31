package edu.globant.easymock.service.impl;

/**
 * Created by federico.calarco on 8/17/2016.
 */
public class UniversityServiceImplTest {

/*    private UniversityServiceImpl universityService;
    private UniversityDao universityDao;

    @Before
    public void setUp() throws Exception {
        universityService = new UniversityServiceImpl();

        universityDao = EasyMock.createMock(UniversityDao.class);

        universityService.setUniversityDao(universityDao);

    }

    @After
    public void tearDown() throws Exception {
        universityDao = null;
        universityService = null;
    }

    @Test
    public void getUniversityById() {

        // expected
        Career career = new Career(1L, "Architecture");
        List<Career> careers = new ArrayList<>();
        careers.add(career);
        University expectedUniversity = new University(1L, "UBA", "La Plata", "55 nro 648", "www.unlp.edu.ar", "221258808", "unlp@edu.ar", careers);


        EasyMock.expect(universityDao.getById(1L)).andReturn(expectedUniversity);
        EasyMock.replay(universityDao);

        // ?
        University university = new University(1L, "UBA", "La Plata", "55 nro 648", "www.unlp.edu.ar", "221258808", "unlp@edu.ar", careers);

        Assert.assertEquals(universityService.getUniversityById(1L).getName(),university.getName());

    }

    @Test(expected = RuntimeException.class)
    public void getUniversityById_nullException(){

        this.universityService.getUniversityById(null);
    }

    @Test(expected = RuntimeException.class)
    public void getUniversityById_returnNull(){
        this.universityService.getUniversityById(2L);
    }*/


}