
main:$(exe_main) $(shared_main) $(static_main)
clean:
	@rm -f $(TARGET)
	@rm -f $(BUILD_OUT_PATH)/*
