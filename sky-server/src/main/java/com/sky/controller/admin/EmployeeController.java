package com.sky.controller.admin;

import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
@Tag(name = "EmployeeController", description = "员工管理")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @Operation(summary = "登录")
    @Parameters({
            @Parameter(name = "employeeLoginDTO", description = "登录信息", required = true)
    })
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        // 登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success();
    }

    /**
     * 新增
     *
     * @param employeeDTO
     * @return
     */
    @PostMapping
    public Result<String> save(@RequestBody EmployeeDTO employeeDTO) {
        log.info("新增员工：{}", employeeDTO);
        employeeService.save(employeeDTO);
        return Result.success();
    }

    /**
     * 员工分页查询
     * 
     * @param employeePageQueryDTO
     * 
     * @return Result<PageResult<Employee>>
     */
    @GetMapping("/page")
    public Result<PageResult<Employee>> page(EmployeePageQueryDTO employeePageQueryDTO) {
        log.info("分页查询员工：{}", employeePageQueryDTO);
        PageResult<Employee> pageQueryData = employeeService.pageQuery(employeePageQueryDTO);
        return Result.success(pageQueryData);
    }

    /**
     * 启用/禁用
     * 
     * @param status
     * @param id
     * @return Result<String>
     */
    @PostMapping("/status/{status}")
    public Result<String> startOrStop(@PathVariable Integer status, long id) {
        log.info("员工启用/禁用：{}, {}", status, id);
        employeeService.startOrStop(status, id);
        return Result.success();
    }

    /**
     * 根据id查询员工
     * 
     * @param id
     * @return Result<Employee>
     */
    @GetMapping("/{id}")
    public Result<Employee> getById(@PathVariable long id) {
        log.info("根据id查询员工：{}", id);
        Employee employee = employeeService.getById(id);
        return Result.success(employee);
    }

    /**
     * 修改
     * 
     * @param employeeDTO
     * @return Result<String>
     */
    @PutMapping
    public Result<String> update(@RequestBody EmployeeDTO employeeDTO) {
        log.info("修改员工：{}", employeeDTO);
        employeeService.update(employeeDTO);
        return Result.success();
    }

}
