package org.judy.algoarena.mappers;

import org.judy.algoarena.dto.submissiontestrun.SubmissionTestRunCreateDTO;
import org.judy.algoarena.dto.submissiontestrun.SubmissionTestRunResponseDTO;
import org.judy.algoarena.models.Submission;
import org.judy.algoarena.models.SubmissionTestRun;
import org.judy.algoarena.models.TestCase;
import org.judy.algoarena.repositories.SubmissionRepository;
import org.judy.algoarena.repositories.TestCaseRepository;
import org.springframework.lang.NonNull;

public class SubmissionTestRunMapper {
    private static SubmissionRepository submissionRepository;
    private static TestCaseRepository testCaseRepository;

    public SubmissionTestRunMapper(
            SubmissionRepository submissionRepository,
            TestCaseRepository testCaseRepository) {
        SubmissionTestRunMapper.submissionRepository = submissionRepository;
        SubmissionTestRunMapper.testCaseRepository = testCaseRepository;
    }

    public static SubmissionTestRunResponseDTO convertToDTO(SubmissionTestRun submissionTestRun) {
        return new SubmissionTestRunResponseDTO(
                submissionTestRun.getId(),
                SubmissionMapper.convertToDTO(submissionTestRun.getSubmission()),
                TestCaseMapper.convertToDTO(submissionTestRun.getTestCase()),
                submissionTestRun.getStatus());
    }

    @NonNull
    public static SubmissionTestRun convertToEntity(SubmissionTestRunCreateDTO submissionTestRunCreateDTO) {
        Long submissionId = submissionTestRunCreateDTO.getSubmissionId();
        if (submissionId == null) {
            throw new IllegalArgumentException("Submission ID cannot be null");
        }
        Submission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Submission not found with ID: " + submissionId));

        Long testCaseId = submissionTestRunCreateDTO.getTestCaseId();
        if (testCaseId == null) {
            throw new IllegalArgumentException("Test case ID cannot be null");
        }
        TestCase testCase = testCaseRepository.findById(testCaseId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Test case not found with ID: " + testCaseId));

        return new SubmissionTestRun(
                submission,
                testCase,
                submissionTestRunCreateDTO.getStatus());
    }
}
