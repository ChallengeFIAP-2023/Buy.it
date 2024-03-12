import styled from 'styled-components/native';

interface Props {
  fullFilled?: boolean;
}

export const Content = styled.View`
  flex: 1;
  height: 400px;
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
  color: ${({ theme, fullFilled }) =>
    fullFilled ? theme.COLORS.WHITE : theme.COLORS.GRAY_300};
  margin-bottom: 5px;
`;
