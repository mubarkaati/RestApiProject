package com.example.toolManagement.service;

import com.example.toolManagement.entities.Tool;
import com.example.toolManagement.repository.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToolRepositoryImplementation {
    @Autowired
    ToolRepository toolRepository;

    public List<Tool> getTools() {
        return toolRepository.findAll();
    }

    public Tool createTool(Tool tool) {
        return toolRepository.save(tool);
    }

    public void deleteTool(Long toolId) {
        toolRepository.deleteById(toolId);
    }

    public Tool updateTool(Tool tool) {
        Tool existingTool = toolRepository.findById(tool.getToolId()).orElse(null);
        if(existingTool != null) {
            existingTool.setToolName(tool.getToolName());
            existingTool.setToolPrice(tool.getToolPrice());
            existingTool.setToolSize(tool.getToolSize());
            existingTool.setToolQuantity(tool.getToolQuantity());
            return toolRepository.save(existingTool);
        }
        return null;
    }
}