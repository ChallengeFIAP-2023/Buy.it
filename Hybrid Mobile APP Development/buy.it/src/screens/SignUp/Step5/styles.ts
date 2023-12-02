import styled from "styled-components/native";

interface Props {
    fulfilled?: boolean;
  }

export const Container = styled.View`
  padding-top: 20px;

  width: 100%;
  height: 100%;

  background-color: ${({ theme }) => theme.COLORS.GRAY_700};
`;

export const Fieldset = styled.View`
  width: 100%;
  margin-bottom: 25px;
`;

export const Requirement = styled.View`
  display: flex;
  flex-direction: row;
  align-items: center;
  gap: 5px;
`;

export const RequirementText = styled.Text<Props>`
  font-family: ${({ theme }) => theme.FONT_FAMILY.ROBOTO.BOLD};
  font-size: ${({ theme }) => theme.FONT_SIZE.MD}px;
  color: ${({ theme, fulfilled }) => fulfilled ? theme.COLORS.WHITE : theme.COLORS.GRAY_300};
  margin-bottom: 5px;
`;
